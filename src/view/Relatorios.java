package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class Relatorios extends JFrame {
	private JButton btnPesquisarAlunosCurso, btnPesquisarAlunosRegiao, btnPesquisarCursosPopulares;
    private JComboBox<String> cmbAlunosCurso;
    private JScrollPane jScrollPane1;
    private JLabel lblAlunosCurso, lblAlunosRegiao, lblCursosPopulares, lblRelatorios, lblVisualizacao;
    private JTextPane txtVisualizacaoRelatorio;
    private JPanel painel;

    public Relatorios() {
		super("Acesso ao Sistema - Relatórios");
		painel = new JPanel();
		
    	lblVisualizacao = new JLabel("Visualização");
        btnPesquisarAlunosCurso = new JButton("PESQUISAR");
        lblAlunosCurso = new javax.swing.JLabel("ALUNOS POR CURSO:");
        lblCursosPopulares = new JLabel("CURSOS MAIS POPULARES:");
        btnPesquisarCursosPopulares = new JButton("PESQUISAR");
        lblAlunosRegiao = new JLabel("ALUNOS POR REGIÃO");
        btnPesquisarAlunosRegiao = new JButton("PESQUISAR");
        cmbAlunosCurso = new JComboBox<>();
        jScrollPane1 = new JScrollPane();
        txtVisualizacaoRelatorio = new JTextPane();
        lblRelatorios = new JLabel("Relatórios");
        
        //Define o gerenciador de layout como null, assim posso colocar os componentes em qualquer lugar do formulário   
        painel.setLayout(null);
        
        //Adiciona os componentes ao formulário
        painel.add( lblRelatorios );
        painel.add( lblCursosPopulares );
        painel.add( btnPesquisarCursosPopulares );
        painel.add( lblAlunosRegiao );
        painel.add( btnPesquisarAlunosRegiao );
        painel.add( lblAlunosCurso );
        painel.add( cmbAlunosCurso );
        painel.add( btnPesquisarAlunosCurso );
        painel.add( lblVisualizacao );
        painel.add( jScrollPane1 );
        
        jScrollPane1.setViewportView(txtVisualizacaoRelatorio);

        //Define o posicionamento dos componentes na tela 
	    //(posição da coluna, posição da linha, comprimento da linha, altura da linha)
        lblVisualizacao.setBounds(340, 30, 129, 15);
        btnPesquisarAlunosCurso.setBounds(10, 250, 120, 40);
        lblAlunosCurso.setBounds(10, 160, 150, 17);
        lblCursosPopulares.setBounds(10, 40, 240, 17);
        btnPesquisarCursosPopulares.setBounds(200, 30, 120, 40);
        lblAlunosRegiao.setBounds(10, 100, 150, 17);
        btnPesquisarAlunosRegiao.setBounds(200, 90, 120, 40);
        cmbAlunosCurso.setBounds(10, 210, 300, 20);
        jScrollPane1.setBounds(340, 50, 380, 250);
        lblRelatorios.setBounds(10, 10, 129, 15); 
        
	    //Define a fonte dos componentes
        lblVisualizacao.setFont(new Font("Tahoma", 1, 12));
        btnPesquisarAlunosCurso.setFont(new Font("Tahoma", 0, 14));
        lblAlunosCurso.setFont(new Font("Tahoma", 0, 14));
        lblCursosPopulares.setFont(new Font("Tahoma", 0, 14));
        btnPesquisarCursosPopulares.setFont(new Font("Tahoma", 0, 14));
        lblAlunosRegiao.setFont(new Font("Tahoma", 0, 14));
        btnPesquisarAlunosRegiao.setFont(new Font("Tahoma", 0, 14));
        lblRelatorios.setFont(new Font("Tahoma", 1, 12));
        
        this.setContentPane(painel);
	    this.pack();
        this.setSize( 750, 350 );
		this.setVisible( true );
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
	}
    
}

