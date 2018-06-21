package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import security.Log;

public class Login extends JFrame {
	private JButton btnAcessar;
    private JLabel lblLogin;
    private JLabel lblLoginSistema;
    private JLabel lblSenha;
    private JPanel painel;
    private JTextField txtLogin;
    private JTextField txtSenha;
    
    public static void main(String[] args) {
    	System.setProperty("apple.laf.useScreenMenuBar", "true");
    	System.setProperty("com.apple.mrj.application.apple.menu.about.name", "WikiTeX");
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
        new Login();
    }
    
	public Login() {	
		super("Acesso ao Sistema - Login");
		painel = new JPanel();
	    
        //Inicializando os componentes
	    lblLoginSistema = new JLabel("LOGIN DO SISTEMA");       
	    lblLogin = new JLabel("Login: ");
		txtLogin = new JTextField(" ");
        lblSenha = new JLabel("Senha: ");
        txtSenha = new JTextField(20);
        btnAcessar = new JButton("ACESSAR");
        btnAcessar.setName("btnacessar");
	    
      //Define o gerenciador de layout como null, assim posso colocar os componentes em qualquer lugar do formulário   
        painel.setLayout(null);
        
        //Adiciona os componentes ao formulário
        painel.add( lblLoginSistema );
	    painel.add( lblLogin );
	    painel.add( txtLogin );
	    painel.add( lblSenha );
	    painel.add( txtSenha );
	    painel.add( btnAcessar );
	    
        //Define o posicionamento dos componentes na tela 
	    //(posição da coluna, posição da linha, comprimento da linha, altura da linha)
	    lblLoginSistema.setBounds(120, 20, 180, 15);
	    lblLogin.setBounds(25, 60, 80, 25);
	    txtLogin.setBounds(115, 60, 250, 25);
	    lblSenha.setBounds(25, 110, 80, 25);
	    txtSenha.setBounds(115, 110, 250, 25);
	    btnAcessar.setBounds(150, 160, 180, 50);
	    
	    //Define a fonte dos componentes
	    lblLoginSistema.setFont(new Font("Tahoma", 1, 18));
	    lblLogin.setFont(new Font("Tahoma", 0, 18));
	    txtLogin.setFont(new Font("Tahoma", 0, 18));
	    lblSenha.setFont(new Font("Tahoma", 0, 18));
        txtSenha.setFont(new Font("Tahoma", 0, 18));
        btnAcessar.setFont(new Font("Tahoma", 1, 18));
	    
        formularios();
        
	    this.setContentPane(painel);
	    this.pack();
        this.setSize( 442, 293 );
		this.setVisible( true );
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void formularios() {
    	btnAcessar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(e.getSource().getClass().getName().equals(JButton.class.getName())){
        			new Aluno();
        			/*String user = Log.hash( txtLogin.getText() );
        			String pass = Log.hash( txtSenha.getText() );
        			if(Log.getLog().validar(user, pass)){
        				dispose();
        			}*/
        		}
        		
        		
        	}
        });
	}
}
