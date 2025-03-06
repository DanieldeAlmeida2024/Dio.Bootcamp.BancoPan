package dio.api_spring_boot.repository;

import dio.api_spring_boot.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {
    public void save(Usuario usuario){
        if(usuario.getId()==null){
            System.out.println("SAVE - Recebendo o usuário na camada de repositorio");
        }else{
            System.out.println("UPDATE - Recebendo o usuário na camada de repositorio");
        }
        System.out.println(usuario);
    }
    public void deleteById(Integer id){
        System.out.println(String.format("DELETE/id - Recebendo o id: %d para exclusao",id));
        System.out.println(id);
    }
    public List<Usuario> findAll(){
        System.out.println("LIST - Listando os usuários do sistema");
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Daniel", "da1234@@"));
        usuarios.add(new Usuario("frank", "masterpass"));
        return usuarios;
    }
    public Usuario findById(Integer id){
        System.out.println(String.format("FIND/id - Recebendo o id: %d para localizar um usuario", id));
        return new Usuario("gleydson", "password");
    }
    public Usuario findByUsername(String username){
        System.out.println(String.format("FIND/username - Recebendo o username: %s para localizar um usuario", username));
        return new Usuario("gleydson", "password");
    }
}
