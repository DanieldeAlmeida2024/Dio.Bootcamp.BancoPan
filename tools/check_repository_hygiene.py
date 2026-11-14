#!/usr/bin/env python3
"""Recusa artefatos locais de IDE/compilação no índice Git."""
from pathlib import PurePosixPath
import subprocess
import sys

GENERATED_DIRS = {"target", "build", "out", ".gradle", ".idea", ".settings"}
GENERATED_NAMES = {".classpath", ".project", ".DS_Store"}
GENERATED_SUFFIXES = (".class", ".iml", ".log", ".pyc")

def is_generated(path: str) -> bool:
    parts = PurePosixPath(path).parts
    return bool(set(parts) & GENERATED_DIRS or parts[-1] in GENERATED_NAMES or path.endswith(GENERATED_SUFFIXES))

def main() -> int:
    tracked = subprocess.check_output(["git", "ls-files", "-z"]).decode("utf-8").split("\0")
    problems = [path for path in tracked if path and is_generated(path)]
    for path in problems:
        print(f"ERRO: artefato indevido versionado: {path}", file=sys.stderr)
    if problems:
        print(f"Total indevido: {len(problems)}", file=sys.stderr)
        return 1
    print("PASS: somente código-fonte, configuração reproduzível e documentação rastreados.")
    return 0

if __name__ == "__main__":
    raise SystemExit(main())
