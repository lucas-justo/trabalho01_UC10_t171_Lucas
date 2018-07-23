package manutencao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class OrdemDao {
    
    public static boolean inserir(Ordem ordem){
        String sql = "INSERT INTO ordens "
            + " ( servico, endereco , codCliente) "
            + " VALUES ( "
            + " '" + ordem.getServico()            + "' , "
            + " '" + ordem.getEndereco()           + "' , "
            + "  " + ordem.getCliente().getCodigo()+ "    "
            + " );";
        return conexao.executar(sql);
    }
    
    
    public static boolean editar(Ordem ordem){
        String sql = "UPDATE clientes SET "
            + " nome =     '"    + ordem.getServico()              + "' , "
            + " endereco = '"    + ordem.getEndereco()             + "' , "
            + " codCliente = "   + ordem.getCliente().getCodigo()  + "   "
            + " WHERE codigo = " + ordem.getCodigo();
        return conexao.executar(sql);
    }
    
    public static boolean excluir(Ordem ordem){
        String sql = "DELETE FROM ordens "
        + " WHERE codigo = " + ordem.getCodigo();
        return conexao.executar(sql);
    }
    
    public static List<Cliente> getClientes(){
        List<Cliente> lista = new ArrayList<>();
        /*String sql = "SELECT c.codigo, c.nome, c.endereco,"
            + " d.codigo, d.nome  "
            + " FROM ordens c  "
            + " INNER JOIN clientes d ON c.codCliente = d.codigo "
            + " WHERE c.tipo = '' "
            + " ORDER BY c.nome"; */
         String sql = "SELECT codigo , nome , email FROM clientes";
        
        ResultSet rs = conexao.consultar( sql );
        if( rs != null ){
            try{
                while ( rs.next() ) {  
                    Cliente cli = new Cliente();
                    cli.setCodigo( rs.getInt(1) );
                    cli.setNome( rs.getString(2) );
                    cli.setEmail( rs.getString(3) );                 
                    
                    lista.add(cli);
                } 
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        } 
        return lista;
    }
    
    
}
