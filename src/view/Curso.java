package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Curso extends JFrame {
    private JButton btnCancelar, btnPesquisar, btnSalvar;
    private JComboBox<String> cmbPeriodo;
    private JLabel lblCadCurso, lblNome, lblPeriodo, lblSigla;
    private JTextField txtNome, txtSigla;
    private JPanel painel;
    
    public Curso() {
		super("Acesso ao Sistema - Cadastro de Cursos");
		painel = new JPanel();
		
    	lblCadCurso = new JLabel("Cadastro de Cursos");
        lblSigla = new JLabel("SIGLA:");
        txtSigla = new JTextField();
        btnPesquisar = new JButton("PESQUISAR");
        lblNome = new JLabel("NOME:");
        txtNome = new JTextField();
        lblPeriodo = new JLabel("PERÍODO:");
        cmbPeriodo = new JComboBox<>();
        btnCancelar = new JButton("CANCELAR");
        btnSalvar = new JButton("SALVAR");
        
        //Define o gerenciador de layout como null, assim posso colocar os componentes em qualquer lugar do formulário   
        painel.setLayout(null);
        
        //Adiciona os componentes ao formulário
        painel.add( lblCadCurso );
        painel.add( lblNome );
        painel.add( txtNome );
        painel.add( btnPesquisar );
        painel.add( lblSigla );
        painel.add( txtSigla );
        painel.add( lblPeriodo );
        painel.add( cmbPeriodo );
        painel.add( btnCancelar );
        painel.add( btnSalvar );
        
        //Define o posicionamento dos componentes na tela 
	    //(posição da coluna, posição da linha, comprimento da linha, altura da linha)
        lblCadCurso.setBounds(10, 10, 129, 15);
        lblSigla.setBounds(10, 70, 50, 15);
        txtSigla.setBounds(60, 70, 210, 20);
        btnPesquisar.setBounds(415, 40, 115, 23);
        lblNome.setBounds(10, 40, 50, 15);
        txtNome.setBounds(60, 40, 350, 20);
        lblPeriodo.setBounds(280, 70, 80, 14);
        cmbPeriodo.setBounds(350, 70, 180, 20);
        btnCancelar.setBounds(320, 110, 100, 50);
        btnSalvar.setBounds(430, 110, 100, 50);
        
	    //Define a fonte dos componentes
        btnCancelar.setFont(new Font("Tahoma", 1, 12));
        btnSalvar.setFont(new Font("Tahoma", 1, 12));

        this.setContentPane(painel);
	    this.pack();
        this.setSize( 560, 220 );
		this.setVisible( true );
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
	}
    
}
