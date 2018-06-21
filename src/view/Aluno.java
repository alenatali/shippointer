package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Aluno extends JFrame {
    private JButton btnAddCurso, btnAddEndereco, btnAddContato, btnCancelar, btnPesquisar, btnSalvar;
    private JComboBox<String> cmbCidade, cmbCurso, cmbEstado, cmbRegiao, cmbStatus;
    private JScrollPane jScrollPane1Matricula, jScrollPane2Contato, jScrollPane3Endereco;
    private JLabel lblBairro, lblCadAluno, lblCelular, lblCep, lblCidade, lblComplemento, lblContato, lblCpf, lblCurso;
    private JLabel lblEmail, lblEndereco, lblEstado, lblIdade, lblLogradouro, lblMatricula, lblNascimento, lblNome, lblNumero;
    private JLabel lblRa, lblRegiao, lblStatus, lblTelefone;
    private JTextField txtBairro, txtCelular, txtCep, txtComplemento, txtCpf, txtEmail, txtIdade, txtLogradouro, txtNascimento;
    private JTextField txtNome, txtNumero, txtRa, txtTelefone;
    private JTable tabContato, tabEndereco, tabMatricula;
    private JPanel painel;
    
    public Aluno() {
		super("Acesso ao Sistema - Cadastro de Alunos");
        painel = new JPanel();
        
        //Inicializando os componentes
        lblCadAluno = new JLabel("Cadastro de Alunos");
        lblNome = new JLabel("NOME:");
        txtNome = new JTextField();
        btnPesquisar = new JButton("PESQ");
        lblCpf = new JLabel("CPF:");
        txtCpf = new JTextField();
        lblNascimento = new JLabel("DATA NASCIMENTO:");
        txtNascimento = new JTextField();
        lblEmail = new JLabel("E-MAIL:");
        txtEmail = new JTextField();
        lblIdade = new JLabel("IDADE:");
        txtIdade = new JTextField();
        lblCelular = new JLabel("CELULAR:");
        cmbCurso = new JComboBox<>();
        lblMatricula = new JLabel("Matrícula:");
        lblLogradouro = new JLabel("LOGRADOURO:");
        txtLogradouro = new JTextField();
        btnAddEndereco = new JButton("ADD ENDEREÇO");
        lblRegiao = new JLabel("REGIÃO:");
        txtCelular = new JTextField();
        lblComplemento = new JLabel("COMPLEMENTO:");
        cmbCidade = new JComboBox<>();
        lblEstado = new JLabel("ESTADO:");
        txtTelefone = new JTextField();
        lblCidade = new JLabel("CIDADE:");
        txtBairro = new JTextField();
        txtComplemento = new JTextField();
        lblCep = new JLabel("CEP:");
        lblBairro = new JLabel("BAIRRO:");
        txtCep = new JTextField();
        btnAddCurso = new JButton("ADD EMPRESA");
        btnCancelar = new JButton("CANCELAR");
        btnSalvar = new JButton("SALVAR");
        lblRa = new JLabel("RA:");
        txtRa = new JTextField();
        lblTelefone = new JLabel("TELEFONE:");
        btnAddContato = new JButton("ADD CONTATO");
        txtNumero = new JTextField();
        jScrollPane1Matricula = new JScrollPane();
        tabMatricula = new JTable();
        lblContato = new JLabel("Contato");
        jScrollPane2Contato = new JScrollPane();
        tabContato = new JTable();
        lblEndereco = new JLabel("Endereço");
        cmbRegiao = new JComboBox<>();
        lblCurso = new JLabel("CURSO:");
        lblStatus = new JLabel("STATUS:");
        cmbStatus = new JComboBox<>();
        jScrollPane3Endereco = new JScrollPane();
        tabEndereco = new JTable();
        cmbEstado = new JComboBox<>();
        lblNumero = new JLabel("NÚMERO:");
        
        tabContato.setModel(new DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                },
                new String [] {
                    "EMAIL", "CELULAR", "TEL. RES/OUTRO"
                }
            ));
        jScrollPane2Contato.setViewportView(tabContato);

        tabEndereco.setModel(new DefaultTableModel(
        		new Object [][] {
        				{null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                    },
                    new String [] {
                        "LOGRADOURO", "NUMERO", "COMPLEMENTO", "ESTADO", "CIDADE", "BAIRRO", "CEP"
                    }
                ));
        jScrollPane3Endereco.setViewportView(tabEndereco);

        tabMatricula.setModel(new DefaultTableModel(
                        new Object [][] {
                            {null, null, null},
                            {null, null, null},
                            {null, null, null},
                        },
                        new String [] {
                            "CURSO", "RA", "STATUS"
                        }
                    ));
        jScrollPane1Matricula.setViewportView(tabMatricula);
        
        //Define o gerenciador de layout como null, assim posso colocar os componentes em qualquer lugar do formulário   
        painel.setLayout(null);
        
        //Adiciona os componentes ao formulário
        painel.add( lblCadAluno ); 
        painel.add( lblNome );
        painel.add( txtNome );
        painel.add( btnPesquisar ); 
        painel.add( lblCpf );
        painel.add( txtCpf );
        painel.add( lblNascimento ); 
        painel.add( txtNascimento ); 
        painel.add( lblIdade );
        painel.add( txtIdade ); 
        painel.add( lblContato ); 
        painel.add( lblEmail );
        painel.add( txtEmail );         
        painel.add( lblCelular );
        painel.add( txtCelular ); 
        painel.add( lblTelefone ); 
        painel.add( txtTelefone ); 
        painel.add( btnAddContato ); 
        painel.add( tabContato ); 
        painel.add( jScrollPane2Contato ); 
        painel.add( lblEndereco ); 
        painel.add( lblLogradouro ); 
        painel.add( txtLogradouro ); 
        painel.add( lblNumero ); 
        painel.add( txtNumero ); 
        painel.add( lblComplemento );  
        painel.add( txtComplemento );
        painel.add( lblRegiao ); 
        painel.add( cmbRegiao ); 
        painel.add( lblEstado );
        painel.add( cmbEstado ); 
        painel.add( lblCidade ); 
        painel.add( cmbCidade ); 
        painel.add( lblBairro ); 
        painel.add( txtBairro );
        painel.add( lblCep );
        painel.add( txtCep ); 
        painel.add( btnAddEndereco ); 
        painel.add( tabEndereco ); 
        painel.add( jScrollPane3Endereco ); 
        painel.add( lblMatricula ); 
        painel.add( lblCurso ); 
        painel.add( cmbCurso ); 
        painel.add( lblRa ); 
        painel.add( txtRa ); 
        painel.add( lblStatus ); 
        painel.add( cmbStatus ); 
        painel.add( btnAddCurso );
        painel.add( tabMatricula ); 
        painel.add( jScrollPane1Matricula ); 
        painel.add( btnCancelar ); 
        painel.add( btnSalvar ); 

        //Define o posicionamento dos componentes na tela 
	    //(posição da coluna, posição da linha, comprimento da linha, altura da linha)
        lblCadAluno.setBounds(10, 10, 129, 15);
        lblNome.setBounds(10, 40, 37, 15);
        txtNome.setBounds(50, 40, 190, 20);
        btnPesquisar.setBounds(240, 40, 83, 23);
        lblCpf.setBounds(340, 40, 30, 15);
        txtCpf.setBounds(370, 40, 80, 20);
        lblNascimento.setBounds(460, 40, 120, 15);
        txtNascimento.setBounds(580, 40, 110, 20);
        lblEmail.setBounds(10, 100, 42, 15);
        txtEmail.setBounds(60, 100, 230, 20);
        lblIdade.setBounds(700, 40, 50, 15);
        txtIdade.setBounds(740, 40, 90, 20);
        lblCelular.setBounds(300, 100, 60, 15);
        cmbCurso.setBounds(60, 430, 230, 20);
        lblMatricula.setBounds(10, 400, 70, 15);
        lblLogradouro.setBounds(10, 250, 98, 15);
        txtLogradouro.setBounds(100, 250, 240, 20);
        btnAddEndereco.setBounds(670, 280, 150, 23);
        lblRegiao.setBounds(670, 250, 60, 15);
        txtCelular.setBounds(360, 100, 90, 20);
        lblComplemento.setBounds(470, 250, 100, 15);
        cmbCidade.setBounds(240, 280, 130, 20);
        lblEstado.setBounds(10, 280, 51, 15);
        txtTelefone.setBounds(570, 100, 90, 20);
        lblCidade.setBounds(190, 280, 46, 15);
        txtBairro.setBounds(430, 280, 90, 20);
        txtComplemento.setBounds(570, 250, 90, 20);
        lblCep.setBounds(530, 280, 30, 15);
        lblBairro.setBounds(380, 280, 50, 15);
        txtCep.setBounds(560, 280, 100, 20);
        btnAddCurso.setBounds(660, 430, 160, 23);
        btnCancelar.setBounds(600, 550, 110, 40);
        btnSalvar.setBounds(720, 550, 96, 40);
        lblRa.setBounds(300, 430, 19, 15);
        txtRa.setBounds(330, 430, 120, 20);
        lblTelefone.setBounds(460, 100, 110, 15);;
        btnAddContato.setBounds(670, 100, 150, 23);
        txtNumero.setBounds(410, 250, 50, 20);
        jScrollPane1Matricula.setBounds(10, 460, 810, 80);
        lblContato.setBounds(10, 70, 51, 15);
        jScrollPane2Contato.setBounds(10, 140, 810, 70);
        lblEndereco.setBounds(10, 220, 70, 15);
        cmbRegiao.setBounds(740, 250, 80, 20);
        lblCurso.setBounds(10, 430, 50, 15);
        lblStatus.setBounds(460, 430, 50, 15);
        cmbStatus.setBounds(520, 430, 130, 20);
        jScrollPane3Endereco.setBounds(10, 310, 810, 80);
        cmbEstado.setBounds(100, 280, 80, 20);
        lblNumero.setBounds(350, 250, 60, 15);
        
	    this.setContentPane(painel);
	    this.pack();
        this.setSize( 860, 640 );
		this.setVisible( true );
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
	}

}

