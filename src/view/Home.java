package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import security.Usuario;

public class Home extends JFrame {
    private JButton btnCadAluno;
    private JButton btnCadCurso;
    private JButton btnFechar;
    private JButton btnRelatorios;
    private JLabel lblDescricao;
    private JLabel lblHome;
    private JPanel painel;

	public Home(Usuario u) {
		super("Acesso ao Sistema - Home");
		painel = new JPanel();
		
        lblHome = new JLabel("HOME");
        btnFechar = new JButton("FECHAR");
        lblDescricao = new JLabel("Olá, seja bem vindo ao sistema de relatórios gerencias de Alunos e Cursos, selecione a opção desejada:");
        btnCadAluno = new JButton("CADASTRO DE ALUNO");
        btnCadCurso = new JButton("CADASTRO DE CURSO");
        btnRelatorios = new JButton("RELATÓRIOS");
        
        //Define o gerenciador de layout como null, assim posso colocar os componentes em qualquer lugar do formulário   
        painel.setLayout(null);
        
        //Adiciona os componentes ao formulário
        painel.add( lblHome );
        painel.add( lblDescricao );
        painel.add( btnCadAluno );
        painel.add( btnCadCurso );
        painel.add( btnRelatorios );
        painel.add( btnFechar );
        
        //Define o posicionamento dos componentes na tela 
	    //(posição da coluna, posição da linha, comprimento da linha, altura da linha)
        lblHome.setBounds(10, 10, 129, 15);
        btnFechar.setBounds(320, 230, 250, 110);
        lblDescricao.setBounds(10, 40, 610, 15);
        btnCadAluno.setBounds(10, 80, 250, 110);
        btnCadCurso.setBounds(320, 80, 250, 110);
        btnRelatorios.setBounds(10, 240, 250, 110);
        
	    //Define a fonte dos componentes
        lblHome.setFont(new Font("Tahoma", 1, 12)); 
        btnFechar.setFont(new Font("Tahoma", 0, 18)); 
        lblDescricao.setFont(new Font("Tahoma", 0, 12)); 
        btnCadAluno.setFont(new Font("Tahoma", 0, 18));
        btnCadCurso.setFont(new Font("Tahoma", 0, 18)); 
        btnRelatorios.setFont(new Font("Tahoma", 0, 18));
        
        formularios();
        
	    this.setContentPane(painel);
	    this.pack();
        this.setSize( 638, 444 );
		this.setVisible( true );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void formularios() {
		
    	btnCadAluno.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Aluno();
        	}
        });
    	
    	btnCadCurso.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Curso();
        	}
        }); 
    	
    	btnRelatorios.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Relatorios();
        	}
        }); 
    	
    	btnFechar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                dispose();
        	}
        }); 
	}
}