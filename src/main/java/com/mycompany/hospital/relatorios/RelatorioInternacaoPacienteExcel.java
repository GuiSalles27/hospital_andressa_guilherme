package com.mycompany.hospital.relatorios;

import com.mycompany.hospital.dao.entidade.Internacao;
import jakarta.servlet.ServletOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class RelatorioInternacaoPacienteExcel {

    private List<Internacao> internacoes;

    public RelatorioInternacaoPacienteExcel(List<Internacao> internacoes) {
        this.internacoes = internacoes;
    }

    public void gerarRelatorio(HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("InternacoesPaciente");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Paciente");
        headerRow.createCell(1).setCellValue("Data/Hora");
        headerRow.createCell(2).setCellValue("Sala");
        headerRow.createCell(3).setCellValue("Descrição");

        int rowCount = 1;
        for (Internacao i : internacoes) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(i.getPacienteInternacao().getNomePaciente());
            row.createCell(1).setCellValue(i.getDataHora() != null ? i.getDataHora() : "");
            row.createCell(2).setCellValue(i.getSala());
            row.createCell(3).setCellValue(i.getDescricao());
        }

        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio_internacoes_paciente.xlsx");

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
        }
        workbook.close();
    }
}
