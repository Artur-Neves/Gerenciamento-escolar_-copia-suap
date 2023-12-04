/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;
import com.mycompany.itex.TableofPDF;
import com.mycompany.projeto_lais.Enums.Unidade_Enum;
import com.mycompany.projeto_lais.Model.Aluno_Atividade_Model;
import com.mycompany.projeto_lais.Model.Aluno_Model;
import com.mycompany.projeto_lais.Model.Atividade_Model;
import com.mycompany.projeto_lais.Model.Aula_Model;
import com.mycompany.projeto_lais.Model.Dao.Aluno_Atividade_dao;
import com.mycompany.projeto_lais.Model.Dao.Aluno_Aula_dao;
import com.mycompany.projeto_lais.Model.Dao.Aluno_dao;
import com.mycompany.projeto_lais.Model.Dao.Atividade_dao;
import com.mycompany.projeto_lais.Model.Dao.Aula_dao;
import com.mycompany.projeto_lais.Model.Dao.Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_dao;
import com.mycompany.projeto_lais.Model.Frequencia_Model;
import com.mycompany.projeto_lais.Model.Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import com.mycompany.projeto_lais.View.Aluno;
import com.mycompany.projeto_lais.View.Atividade;
import com.mycompany.projeto_lais.View.Aula;
import com.mycompany.projeto_lais.View.Gerar_PDF;
import com.mycompany.projeto_lais.View.Menssagem_De_Confirmacao;
import com.mycompany.projeto_lais.View.Relatorio;
import com.mycompany.projeto_lais.View.Turma;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Artur
 */
public class Relatorio_Controller {
    private final Relatorio view;
    private Aluno_dao dao_aluno;
    private Atividade_dao dao_atividade;
    private Aula_dao dao_aula;
    private Materia_dao dao_materia;
    private Turma_dao dao_turma;
    private Turma_Materia_dao dao_tm;
    private Aluno_Atividade_dao dao_at;
    private Aluno_Aula_dao dao_aa;
    private Turma_Materia_Model turmamateria;
    private List<Atividade_Model> list_atividade;
    private List<Aluno_Model> list_aluno;
    private List<Aula_Model> list_aula;
    private SimpleDateFormat formato; 
    private List<Materia_Model> list_Materia;
    private List<Turma_Model> list_Turma;
    private Materia_Model materia;
    private Turma_Model turma;
    private DefaultTableModel dm;
    private List<Aluno_Atividade_Model> list_at;
    private List<Frequencia_Model> list_aa;
    private double nota1;
    private double nota2;
    private double nota3;
    private double media;
    private int[] faltas;
    private int frequencia;
    private boolean termino;
    private DecimalFormat df;
    private int i;
    private String setor;

    public Relatorio_Controller(Relatorio view, Turma_Materia_Model turmamateria) {
        this.view = view;
        this.dao_aluno = new Aluno_dao();
        this.dao_atividade = new Atividade_dao();
        this.dao_aula = new Aula_dao();
        this.dao_materia = new Materia_dao();
        this.dao_turma = new Turma_dao();
        this.dao_tm = new Turma_Materia_dao();
        this.dao_at = new Aluno_Atividade_dao();
        this.dao_aa = new Aluno_Aula_dao();
        this.turmamateria = turmamateria;
        this.dm = new DefaultTableModel();
        this.list_atividade = new ArrayList<>();
        this.list_aula = new ArrayList<>();
        this.list_aluno = new ArrayList<>();
        this.formato = new SimpleDateFormat("dd/MM/yyyy");
        this.list_Turma = new ArrayList<>();
        this.list_Materia =new ArrayList<>();
        this.materia= null;
        this.turma= null;
        this.list_at = new ArrayList<>();
        this.df = new DecimalFormat("#.##"); 
        i=1;
    }
    
    
    public void start(){
        view.setExtendedState(Relatorio.MAXIMIZED_BOTH);
         view.getjComboBox2().removeAllItems();
         list_Materia.clear();
        for (Materia_Model materia:dao_materia.selectAll()){
            view.getjComboBox2().addItem(materia.getNome());
            list_Materia.add(materia);
        }
        view.getjComboBox2().setSelectedIndex(0);
        atualizarTurma();
        view.getjComboBox3().removeAllItems();
        view.getjComboBox3().addItem("");
        for (Unidade_Enum b :Unidade_Enum.values()){
            view.getjComboBox3().addItem(b.toString());
        }
    }
     public void voltar() {
        Turma t = new Turma(turmamateria.getMateria());
        t.setVisible(true);
        view.hide();
    }
    
     public void entrarAula() {
       Aula aula = new Aula(turmamateria);
       aula.setVisible(true);
       view.hide();
    }
    public void entrarAtividade() {
           Atividade atividade = new Atividade(turmamateria);
       atividade.setVisible(true);
       view.hide();   
    }
    public void entrarAluno() {
        Aluno aluno = new Aluno(turmamateria.getTurma(), turmamateria.getMateria());
        aluno.setVisible(true);
        view.hide();
    }

    public void atualizar() {
        i=1;
        if (list_Materia.size()!=0 && list_Turma.size()!=0 && view.getjComboBox1().getSelectedIndex()!=-1){
        materia = list_Materia.get(view.getjComboBox2().getSelectedIndex());
        turma = list_Turma.get(view.getjComboBox1().getSelectedIndex());
        }
       if (view.getjRadioButton1().isSelected()){
           view.getjComboBox3().setVisible(false);
           view.getjLabel2().setVisible(false);
          atualizarAluno();
          setor= "Alunos";
       }
       else if (view.getjRadioButton2().isSelected()){
           view.getjComboBox3().setVisible(true);
           view.getjLabel2().setVisible(true);
            atualizarAtividade();
            setor="Atividades";
       }
       else {
           view.getjComboBox3().setVisible(true);
           view.getjLabel2().setVisible(true);
           atualizarAula();
           setor="Aulas";
       }
    }
    
     public void atualizarAtividade() {
         dm = new DefaultTableModel();
         String peso= "NULL";
         String divisor= "NULL";
 String[] dados = new String [8];
 String unidade = ""+view.getjComboBox3().getSelectedItem();
 Turma_Materia_Model tm = dao_tm.findforMateriaAndTurma(materia, turma);
 List<Atividade_Model> lista= dao_atividade.findByTurmaMateriaUnidade(tm, unidade);
 list_atividade.clear();
 dm.addColumn("#");
 dm.addColumn("Descrição");
 dm.addColumn("Cálculo");
 dm.addColumn("Peso");
 dm.addColumn("Divisor");
 dm.addColumn("Valor");
  dm.addColumn("Unidade"); 
  dm.addColumn("Data");
  view.getjTable1().setModel(dm);
  System.out.println("turma"+materia.getNome());
  System.out.println("Materia"+turma.getNome());
         System.out.println("Tamanho da lista "+lista.size());
 if (lista.size()!=0){
        for (Atividade_Model  atividade : lista) {
            peso= "NULL";
            divisor="NULL";
            System.out.println("entrando");
            dados[0] = ""+i++;
            dados[1] = atividade.getDescricao();
            dados[2] = ""+atividade.getCalculo();
            if (atividade.getCalculo().equals("Soma com Divisor Informado"))
                divisor= ""+atividade.getDivisor();
            else if (atividade.getCalculo().equals("Média Ponderada"))
                peso = ""+atividade.getPeso();
            
            dados[3] = peso;
            dados[4] = divisor;
            dados[5] = ""+atividade.getValor();
            dados[6] = atividade.getUnidade();
            dados[7] = formato.format(atividade.getData());
            dm.addRow(dados);
            list_atividade.add(atividade);
        }
view.getjTable1().setModel(dm);
    }
       view.getjTable1().getColumnModel().getColumn(0).setMaxWidth(80);
         view.getjTable1().getColumnModel().getColumn(0).setMinWidth(250);
         view.getjTable1().getColumnModel().getColumn(1).setMaxWidth(500);
         view.getjTable1().getColumnModel().getColumn(1).setMinWidth(650);
     
     }
     public void atualizarAula() {
   dm = new DefaultTableModel();
   
 String[] dados = new String [7];
 String unidade = ""+view.getjComboBox3().getSelectedItem();
 Turma_Materia_Model tm = dao_tm.findforMateriaAndTurma(materia, turma);
 List<Aula_Model> lista = dao_aula.findByTurmaMateria(tm, unidade);
 dm.addColumn("#");
 dm.addColumn("Data");
 dm.addColumn("Unidade");
 dm.addColumn("Quantidade");
 dm.addColumn("formato");
 dm.addColumn("conteudo");
  dm.addColumn("Observações"); 

 if(lista!=null){
        for (Aula_Model  aula : lista) {
            dados[0] = ""+i++;
            dados[1] = ""+formato.format(aula.getData());
            dados[2] = aula.getUnidade();
            dados[3] = ""+aula.getQuantidade();
            dados[4] = aula.getFormato();
            dados[5] = aula.getConteudo();
            dados[6] = aula.getObs();
            dm.addRow(dados);     
        }
}view.getjTable1().setModel(dm);
view.getjTable1().getColumnModel().getColumn(0).setMaxWidth(80);
         view.getjTable1().getColumnModel().getColumn(0).setMinWidth(250);
 view.getjTable1().getColumnModel().getColumn(5).setMaxWidth(500);
         view.getjTable1().getColumnModel().getColumn(5).setMinWidth(550);
         view.getjTable1().getColumnModel().getColumn(6).setMaxWidth(500);
         view.getjTable1().getColumnModel().getColumn(6).setMinWidth(550);
         view.getjTable1().getColumnModel().getColumn(1).setMaxWidth(125);
         view.getjTable1().getColumnModel().getColumn(1).setMinWidth(150);
    }
     public void atualizarAluno() {
         list_aluno = dao_aluno.findByTurma(turma);
        termino= false;
        valor_Maximo();
        
       dm = new DefaultTableModel();
            view.getjTable1().setModel(dm);
        dm.addColumn("#");
        dm.addColumn("Nome");
        dm.addColumn("faltas");
        dm.addColumn("Frequência");
        dm.addColumn("Situação");
        dm.addColumn("N1");
        dm.addColumn("N2");
        dm.addColumn("N3");
        dm.addColumn("Recuperação");
        dm.addColumn("Média");
        
        
        String[] dados = new String[10];
        for (Aluno_Model a : list_aluno) {
            a.setSituacao(passarOrNot(a, media, media));
            double recuperacao = 0;
            nota1 = soma_Das_Notas(a, "Unidade 1");
            nota2 = soma_Das_Notas(a, "Unidade 2");
            nota3 = soma_Das_Notas(a, "Unidade 3");
            faltas = faltas(a);
            frequencia = 100-((faltas[0]*100)/faltas[1]);
            media = (nota1+nota2+nota3)/3;
            if (dao_at.findByAlunoUnidade(a, "Recuperação", turmamateria).size()!=0)
            recuperacao = dao_at.findByAlunoUnidade(a, "Recuperação", turmamateria).get(0).getValor_recebido();
             a.setSituacao(passarOrNot(a, media, recuperacao));
             dao_aluno.update(a);
             dados[0] = ""+i++;
            dados[1] = a.getNome();
            dados[2] = ""+faltas[0];
            dados[3] = frequencia+"%";
            dados[4] = a.getSituacao();
            // nota 1
            dados[5] = ""+df.format(nota1);
            // nota 2
            dados[6] = ""+df.format(nota2);
            // nota 3
            dados[7] = ""+df.format(nota3);
            // recuperação
            dados[8] = ""+recuperacao;
            // media
            dados[9] = " "+df.format(media);
            dm.addRow(dados);
        }
        
        view.getjTable1().setModel(dm);
         view.getjTable1().getColumnModel().getColumn(0).setMaxWidth(80);
         view.getjTable1().getColumnModel().getColumn(0).setMinWidth(250);
    }
     public void valor_Maximo() {
       double valor_total = 0;
        double valor_restante = 30;
        double divisor = 1;
        
         for (int i=1; i<4; i++){
             Turma_Materia_Model tm = dao_tm.findforMateriaAndTurma(materia, turma);
        list_atividade = dao_atividade.findByTurmaMateriaUnidade(tm, "Unidade "+i);
        if (list_atividade.size()!=0 &&  list_atividade.get(0).getCalculo().equals("Maior Nota"))
            valor_restante = valor_restante-10;
         valor_total = 0;
         divisor = 1;
        for (Atividade_Model atvd : list_atividade) {
            switch (atvd.getCalculo()) {
                case "Soma simples":
                    valor_total = valor_total + atvd.getValor();
                    

                    break;
                case "Média Aritmética":
                    valor_total = valor_total;
                   
                    break;
                case "Média Ponderada":
                    valor_total = valor_total + atvd.getPeso();
               

                    break;
                case "Soma com Divisor Informado":
                    divisor = atvd.getDivisor();
                    valor_total = valor_total + atvd.getValor();
               

                    break;
                case "Maior Nota":
                    valor_total = valor_total;
                    
                    break;
                default:
                    break;
            }
        }
        System.out.println("Aluno: valor total " + (valor_total) / divisor);
        valor_restante = valor_restante - (valor_total) / divisor;
        System.out.println("Aluno: Valor restante" + valor_restante);}
        if (valor_restante==0.0){
            termino=true;
            System.out.println("termino "+termino);
        }

    }
     public double soma_Das_Notas(Aluno_Model aluno, String unidade){
       list_at = dao_at.findByAlunoUnidade(aluno, unidade, turmamateria); 
       double valor =0;
       double quantidade=0;
       double valor_peso= 0;
       double maior = -1;
       if (list_at!=null){
        for (Aluno_Atividade_Model at: list_at){
             switch (list_at.get(0).getAtividade().getCalculo()) {
            case "Soma simples":
                valor = valor+at.getValor_recebido();
                quantidade=1;
                System.out.println("ta caindo aqui");
                break;
                 case "Média Aritmética":
                  quantidade++;
                  valor = valor+ at.getValor_recebido();
                    break;
                    case "Média Ponderada":
                   quantidade=1;
                   valor_peso= at.getAtividade().getPeso()*(at.getValor_recebido()/10);
                   valor = valor+valor_peso;
                    break;
                    case "Soma com Divisor Informado":
                    quantidade=at.getAtividade().getDivisor();
                    valor = valor+at.getValor_recebido();
                    
                    break;
                    case "Maior Nota":
                        quantidade=1;
                   if (at.getValor_recebido()>maior){
                       maior = at.getValor_recebido();
                   }
                   valor = maior;
            default:
                break;
        }
        }}
        return valor/quantidade;
    }
     public int[] faltas(Aluno_Model aluno){
        int[] quantidade_faltas = new int[2];
       quantidade_faltas[0]=0;
       quantidade_faltas[1]=0;

      
        list_aa = dao_aa.findByAluno(aluno, turmamateria);
            for (Frequencia_Model f : list_aa){
            quantidade_faltas[0] = quantidade_faltas[0] + f.getFaltas();
            quantidade_faltas[1] = quantidade_faltas[1] +f.getIdAula().getQuantidade();
              }
            if (quantidade_faltas[1]==0.0){
                quantidade_faltas[1]=1;
            }
            return quantidade_faltas;
    }
     public String passarOrNot(Aluno_Model aluno, double media, double recuperacao){
        String situacao;
        if (termino){
        if (media>=5){
            situacao = "Aluno aprovado por nota";
        }
        else {
            if (recuperacao>=5){
                situacao = "Aluno aprovado por recuperação";
            }
            else{
                situacao = "Aluno reprovado";
            }
        }}
        else {
            situacao = "Cursando";
        }
        return situacao;
    }
    public void atualizarTurma(){
        view.getjComboBox1().removeAllItems();
        list_Turma.clear();
        for (Turma_Model turma : dao_materia.findByName(""+view.getjComboBox2().getSelectedItem()).getTurma()){
            view.getjComboBox1().addItem(turma.getNome());
            list_Turma.add(turma);
        }
        

    }
    public void iniciarAtividade(){
         view.getjComboBox3().setVisible(true);
    }
    public void iniciarAula(){
         view.getjComboBox3().setVisible(true);
    }
    public void gerarPDF() {
        Gerar_PDF g = new Gerar_PDF(null, true);
        g.setVisible(true);
        if (g.c.isValido()){
            
               try {
               TableofPDF t = new TableofPDF(g.c.getPath(), g.c.getNomeFile());
               t.cabecalho(g.c.getTitulo(), g.c.getProfessor(), turma.getNome(), materia.getNome());
               t.corpo(setor, pegarTabela());
               t.rodape(g.c.getCidade(), g.c.getEstado());
               view.imprimirNaTela("O PDF com o título '"+g.c.getTitulo()+"' foi gerado com sucesso!", setor);
               } catch (FileNotFoundException ex) {
                    Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "Caminho inexistente", "", "Atenção", 0);
               m.setVisible(true);
               }
            
          
            
            
        }}
        public List<String> pegarTabela(){
          List<String> tabela = new ArrayList<>();
          for (int linha=0; linha< view.getjTable1().getRowCount();linha++){
              for(int coluna=0; coluna < view.getjTable1().getColumnCount(); coluna++){
                  tabela.add(""+view.getjTable1().getValueAt(linha, coluna));
              }
          }
            return tabela;
        }
    

   
}
