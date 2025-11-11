package com.mycompany.hospital.relatorios;

import com.mycompany.hospital.dao.entidade.Consulta;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class RelatorioConsultasExcel {

    private final List<Consulta> consultas;

    public RelatorioConsultasExcel(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public void gerarRelatorio(HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Consultas");

        // Criação do cabeçalho
        Row headerRow = sheet.createRow(0);
        String[] cabecalhos = {"Código", "Data da Consulta", "Diagnóstico", "Paciente", "Funcionário"};
        for (int i = 0; i < cabecalhos.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(cabecalhos[i]);
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            cell.setCellStyle(style);
        }

        // Formatar data
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Linhas de dados
        int rowCount = 1;
        for (Consulta c : consultas) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(c.getCodConsulta());
            row.createCell(1).setCellValue(c.getDataConsulta() != null ? sdf.format(c.getDataConsulta().getTime()) : "");
            row.createCell(2).setCellValue(c.getDiagnostico() != null ? c.getDiagnostico() : "");
            row.createCell(3).setCellValue(c.getPacienteConsulta() != null ? c.getPacienteConsulta().getNomePaciente() : "");
            row.createCell(4).setCellValue(c.getFuncionarioConsulta() != null ? c.getFuncionarioConsulta().getNomeFunc() : "");
        }

        // Ajustar colunas automaticamente
        for (int i = 0; i < cabecalhos.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Configurar resposta HTTP
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio_consultas.xlsx");

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
        }

        workbook.close();
    }
}
