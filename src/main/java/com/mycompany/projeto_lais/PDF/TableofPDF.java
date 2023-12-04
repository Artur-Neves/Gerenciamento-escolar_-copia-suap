/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.itex;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



/**
 *
 * @author g15
 */
public class TableofPDF {
  private final  Document doc;
  private SimpleDateFormat formato; 
  private DeviceRgb cor;
  private boolean mudar;
  private int i;
    /**
     * @param args the command line arguments
     */
    
  
    public TableofPDF(String path, String arquivo) throws FileNotFoundException{
        
        this.doc=new Document( new PdfDocument( new PdfWriter(path+"\\"+arquivo)));
        this.formato = new SimpleDateFormat("dd/MM/yyyy");
    }

    public void linha() {
         float coluna[] = {800f};
            Table tabela = new Table(coluna);
            tabela.setMargin(30f);
            tabela.addCell(new Cell().add(new Paragraph(""))
                    .setBorderTop(Border.NO_BORDER).setBorderRight(Border.NO_BORDER)
                    .setBorderLeft(Border.NO_BORDER).setMarginTop(30).setBorderLeft(Border.NO_BORDER).setMarginBottom(30));
            doc.add(tabela);
    }
    public void tabelaAlunos(List<String> celloftables){
        float [] columns = {20f, 100f, 50f, 60f, 125f, 50f, 50f, 50f, 50f, 50f};
        Table tabelaAlunos = new Table(columns);
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("#"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("Nome"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("N.Faltas"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("Frequência"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("Situação"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
         tabelaAlunos.addCell(new Cell().add(new  Paragraph("N1"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("N2"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("N3"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
         tabelaAlunos.addCell(new Cell().add(new  Paragraph("Recuperação"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("Média"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));

        for (String celula : celloftables){
          
           if (i==10){
               i=0;
               mudar = !mudar; 
            }
           i++;
            if (mudar){
                cor = new DeviceRgb(169,169,169);
              
            }
            else{
                cor =  new DeviceRgb(220,220,220);
                     
            }
               tabelaAlunos.addCell(new Cell().add(new  Paragraph(celula))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(8).setBackgroundColor( cor));
            
        }
  doc.add(tabelaAlunos);
    }
    public void tabelaAtividades(List<String> celloftables){
           float [] columns = {20f, 170f, 125, 40f, 40f, 40f, 75f, 50f};
        Table tabelaAlunos = new Table(columns);
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("#"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("Descrição"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("Cálculo"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("Peso"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
         tabelaAlunos.addCell(new Cell().add(new  Paragraph("Divisor"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
       
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("Valor"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("Unidade"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("Data"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
       for (String celula : celloftables){
          
           if (i==8){
               i=0;
               mudar = !mudar; 
            }
           i++;
            if (mudar){
                cor = new DeviceRgb(169,169,169);
              
            }
            else{
                cor =  new DeviceRgb(220,220,220);
                     
            }
               tabelaAlunos.addCell(new Cell().add(new  Paragraph(celula))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(8).setBackgroundColor( cor));
            
        }
  doc.add(tabelaAlunos);
    }
    public void tabelaAulas(List<String> celloftables){
           float [] columns = {20f, 50f, 75f, 40f, 75f,  150f, 150f};
        Table tabelaAlunos = new Table(columns);
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("#"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("Data"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("Unidade"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("Quantidade"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
        tabelaAlunos.addCell(new Cell().add(new  Paragraph("Formato"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
         tabelaAlunos.addCell(new Cell().add(new  Paragraph("Conteúdo"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
   tabelaAlunos.addCell(new Cell().add(new  Paragraph("observações"))
                .setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10).setBackgroundColor( new DeviceRgb(169,169,169)));
   
          for (String celula : celloftables){
          
           if (i==7){
               i=0;
               mudar = !mudar; 
            }
           i++;
            if (mudar){
                cor = new DeviceRgb(169,169,169);
              
            }
            else{
                cor =  new DeviceRgb(220,220,220);
                     
            }
               tabelaAlunos.addCell(new Cell().add(new  Paragraph(celula))
                .setTextAlignment(TextAlignment.CENTER).setFontSize(8).setBackgroundColor( cor));
            
        }
  doc.add(tabelaAlunos);
    }
    public void cabecalho(String titulo, String professor, String turma, String materia){
        try {

            // declaração dos parágrafos
            Paragraph tituloP = new Paragraph(titulo).setBold();
            Paragraph professorP = new Paragraph(professor);
            Paragraph turmaMateriaP = new Paragraph("Turma: "+turma+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  Matéria: "+ materia);
            Paragraph dataP = new Paragraph("Data de emissão: "+ new Date());
            
            // alinhamento
            tituloP.setTextAlignment(TextAlignment.CENTER);
            professorP.setTextAlignment(TextAlignment.CENTER);
            turmaMateriaP.setTextAlignment(TextAlignment.CENTER);
         
            //dataP.setTextAlignment(TextAlignment.RIGHT);
            
            // tamanho
            tituloP.setFontSize(30);
            professorP.setFontSize(15);
            turmaMateriaP.setFontSize(13);
            dataP.setFontSize(13);
            
            
            // margin 
            professorP.setMarginBottom(40);
            
           
            // adcionando ao documento
            doc.add(tituloP);
            doc.add(professorP);
            doc.add(turmaMateriaP);
            linha();
            
           
           
            
        }
         catch (Exception e) {
             System.out.println("erro "+e.getMessage());
        }
        
    }
    public void corpo(String setor , List<String> celloftables){
      mudar=false;
       i = 0;
        switch (setor) {
            case "Alunos":
                Paragraph titulo = new Paragraph(setor);
                titulo.setTextAlignment(TextAlignment.CENTER)
                        .setBold().setFontSize(25).setMarginBottom(20);
                doc.add(titulo);
                tabelaAlunos(celloftables);
                break;
                case "Atividades":
                Paragraph titulo2 = new Paragraph(setor);
                titulo2.setTextAlignment(TextAlignment.CENTER)
                        .setBold().setFontSize(25).setMarginBottom(20);
                doc.add(titulo2);
                tabelaAtividades(celloftables);
                break;
                case "Aulas":
                Paragraph titulo3 = new Paragraph(setor);
                titulo3.setTextAlignment(TextAlignment.CENTER)
                        .setBold().setFontSize(25).setMarginBottom(20);
                doc.add(titulo3);
                tabelaAulas(celloftables);
                break;
            default:
                throw new AssertionError();
        } 
    }
    public void rodape(String cidade, String estado){
        try {
            linha();
            Paragraph direitos = new Paragraph("Todos os direitos Reservados - 2023 ©: Artur Neves Almeida");
            Paragraph local = new Paragraph(cidade+"-"+estado);
            Paragraph data = new Paragraph(formato.format(new Date()));
            //Alinhamento
            direitos.setTextAlignment(TextAlignment.RIGHT);
            local.setTextAlignment(TextAlignment.CENTER);
            data.setTextAlignment(TextAlignment.CENTER);
            //font 
            direitos.setFontSize(8);
            local.setFontSize(8);
            data.setFontSize(8);
            //adicionar ao documento
            doc.add(direitos);
            doc.add(local);
            doc.add(data);
            doc.close();
        } catch (Exception e) {
            System.out.println("erro "+e.getMessage());
        }
        
    }
    
    
}
