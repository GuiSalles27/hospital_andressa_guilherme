package com.mycompany.hospital.relatorios;

import com.mycompany.hospital.dao.entidade.Internacao;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RelatorioInternacaoExcel {

    public void exportarInternacoes(List<Internacao> internacoes, OutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Internações");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Paciente");
        header.createCell(1).setCellValue("Funcionário");
        header.createCell(2).setCellValue("Data/Hora");
        header.createCell(3).setCellValue("Descrição");
        header.createCell(4).setCellValue("Sala");

        int rowCount = 1;
        for (Internacao i : internacoes) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(i.getPacienteInternacao().getNomePaciente());
            row.createCell(1).setCellValue(i.getFuncionarioInternacao().getNomeFunc());
            row.createCell(2).setCellValue(i.getDataHora());
            row.createCell(3).setCellValue(i.getDescricao());
            row.createCell(4).setCellValue(i.getSala());
        }

        workbook.write(outputStream);
        workbook.close();
    }
}
