package modelo;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ExportarPdf {

	public static void gerarPdf(Vector<Relatorio> vetorRel) throws DocumentException, IOException {
		Document doc = null;
		OutputStream os = null;
		String caminho = System.getProperty("user.dir");
		
		
		int agendados = 0;
		int atendidos = 0;
		int cancelados = 0;
		int espera = 0;
		//contar a quantidade de cada status de agendamento
		for (int i = 0; i < vetorRel.size(); i++) {
			Relatorio relatorio = vetorRel.get(i);
			if (relatorio.getStatus().equals("AGENDADO")) {
				agendados++;
			} else if (relatorio.getStatus().equals("ATENDIDO")) {
				atendidos++;
			} else if (relatorio.getStatus().equals("CANCELADO")) {
				cancelados++;
			} else {
				espera++;
			}

		}
		
		//pegando a data atual e formatando, para salvar o aquivo com data e hora
		SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy HH.mm");
		String data = formatoData.format(new Date());

		try {

			// cria o documento tamanho A4, margens de 2,54cm
			doc = new Document(PageSize.A4.rotate());

			// cria a stream de sa�da com a data e hora
			os = new FileOutputStream(caminho+"\\relatorio "+data+".pdf");
			

			// associa a stream de sa�da ao
			PdfWriter.getInstance(doc, os);

			// abre o documento
			doc.open();

			//pega o caminho da imagem e colocar a imagem no PDF
						
			Image img = Image.getInstance(caminho+"\\br\\image\\Logo_entalhe_403x132.fw.png");
			img.setAlignment(Element.ALIGN_CENTER);
			doc.add(img);
			
			//Formatando o texto do titulo e colocando ele no PDF
			Font f = new Font(FontFamily.COURIER, 13, Font.BOLD);
			Font f2 = new Font(FontFamily.COURIER, 30, Font.BOLD);
			Font f3 = new Font(FontFamily.COURIER, 11, Font.BOLD);
			Paragraph titulo = new Paragraph("RELAT�RIO DE ATENDIMENTOS", f2);
			titulo.setAlignment(Element.ALIGN_CENTER);
			titulo.setSpacingAfter(40);
			titulo.setSpacingBefore(40);
			doc.add(titulo);
			
			// PdfPTable table = new PdfPTable(new float[] { 0.1f, 0.1f, 0.1f,
			// 0.2f, 0.1f, 0.2f, 0.1f, 0.1f });

			PdfPTable table = new PdfPTable(8);
			PdfPCell header;
			
			//Criando o nome de cada coluna das tabelas e colorindo
			header = new PdfPCell(new Paragraph("Data", f));
			header.setBackgroundColor(BaseColor.YELLOW);
			header.setVerticalAlignment(Element.ALIGN_CENTER);			
			header.setColspan(1);
			table.addCell(header);
			header = new PdfPCell(new Paragraph("Inicio", f));
			header.setBackgroundColor(BaseColor.YELLOW);
			header.setVerticalAlignment(Element.ALIGN_CENTER);
			header.setColspan(1);
			table.addCell(header);
			header = new PdfPCell(new Paragraph("Fim", f));
			header.setBackgroundColor(BaseColor.YELLOW);
			header.setVerticalAlignment(Element.ALIGN_CENTER);
			header.setColspan(1);
			table.addCell(header);
			header = new PdfPCell(new Paragraph("Funcion�rio", f3));
			header.setBackgroundColor(BaseColor.YELLOW);
			header.setVerticalAlignment(Element.ALIGN_CENTER);
			header.setColspan(1);
			table.addCell(header);
			header = new PdfPCell(new Paragraph("Cliente", f));
			header.setBackgroundColor(BaseColor.YELLOW);
			header.setVerticalAlignment(Element.ALIGN_CENTER);
			header.setColspan(1);
			table.addCell(header);
			header = new PdfPCell(new Paragraph("Unidade", f));
			header.setBackgroundColor(BaseColor.YELLOW);
			header.setVerticalAlignment(Element.ALIGN_CENTER);
			header.setColspan(1);
			table.addCell(header);
			header = new PdfPCell(new Paragraph("Servi�os", f));
			header.setBackgroundColor(BaseColor.YELLOW);
			header.setVerticalAlignment(Element.ALIGN_CENTER);
			header.setColspan(1);
			table.addCell(header);
			header = new PdfPCell(new Paragraph("Status", f));
			header.setBackgroundColor(BaseColor.YELLOW);
			header.setVerticalAlignment(Element.ALIGN_CENTER);
			header.setColspan(1);
			table.addCell(header);

			
			
			//preenchendo o arquivo pdf com os atendimentos
			for (int i = 0; i < vetorRel.size(); i++) {
				Relatorio relatorio = vetorRel.get(i);
				table.addCell(relatorio.getData());
				table.addCell(relatorio.getHoraInicio());
				table.addCell(relatorio.getHoraFim());
				table.addCell(relatorio.getFuncionario());
				table.addCell(relatorio.getCliente());
				table.addCell(relatorio.getUnidade());
				table.addCell(relatorio.getServico());
				table.addCell(relatorio.getStatus());
				
			}
			doc.add(table);

			// adiciona o texto ao PDF
			Paragraph p = new Paragraph("AGENDADOS: " + agendados + "\nATENDIDOS: " + atendidos + "\nCANCELADOS: "
					+ cancelados + "\nEM ESPERA: " + espera+ "\nTOTAL: "+vetorRel.size());
			p.setSpacingBefore(20);
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);

			JOptionPane.showMessageDialog(null, "Gerado relat�rio em PDF", "Gord�o Barbearia",
					JOptionPane.INFORMATION_MESSAGE);
			
			java.awt.Desktop.getDesktop().open(new File("relatorio "+data+".pdf"));
			
		} finally {
			if (doc != null) {
				// fechamento do documento
				doc.close();
			}

			if (os != null) {
				// fechamento da stream de sa�da
				os.close();

			}

		}
	}

	public static void main(String[] args) throws Exception {

	}

}
