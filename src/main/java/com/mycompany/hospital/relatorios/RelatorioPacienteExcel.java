package com.mycompany.hospital.relatorios;

import com.mycompany.hospital.dao.entidade.Paciente;
import jakarta.servlet.ServletOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RelatorioPacienteExcel {

    private List<Paciente> pacientes;

    public RelatorioPacienteExcel(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public void gerarRelatorio(HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Pacientes");

        // Cabeçalho
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nome");
        headerRow.createCell(2).setCellValue("CPF");
        headerRow.createCell(3).setCellValue("Nascimento");
        headerRow.createCell(4).setCellValue("Filiação");
        headerRow.createCell(5).setCellValue("RG");
        headerRow.createCell(6).setCellValue("Endereço");
        headerRow.createCell(7).setCellValue("Bairro");
        headerRow.createCell(8).setCellValue("Cidade");
        headerRow.createCell(9).setCellValue("CEP");
        headerRow.createCell(10).setCellValue("UF");
        headerRow.createCell(11).setCellValue("Telefone");
        headerRow.createCell(12).setCellValue("Plano");

        // Dados
        int rowCount = 1;
        for (Paciente p : pacientes) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(p.getCodPaciente());
            row.createCell(1).setCellValue(p.getNomePaciente());
            row.createCell(2).setCellValue(p.getCpf());
            row.createCell(3).setCellValue(
                p.getDataNascimento() != null ? p.getDataNascimento().getTime().toString() : ""
            );
            row.createCell(4).setCellValue(p.getFiliacao());
            row.createCell(5).setCellValue(p.getRg());
            row.createCell(6).setCellValue(p.getEndereco());
            row.createCell(7).setCellValue(p.getBairro());
            row.createCell(8).setCellValue(p.getCidade());
            row.createCell(9).setCellValue(p.getCep());
            row.createCell(10).setCellValue(p.getUf());
            row.createCell(11).setCellValue(p.getTelefone());
            row.createCell(12).setCellValue(
                p.getPlanoPaciente() != null ? p.getPlanoPaciente().getNomePlano() : "Sem Plano"
            );
        }

        // Ajusta colunas
        for (int i = 0; i <= 12; i++) {
            sheet.autoSizeColumn(i);
        }

        // Configuração HTTP
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio_pacientes.xlsx");

        try (ServletOutputStream os = response.getOutputStream()) {
            workbook.write(os);
        }

        workbook.close();
    }
}
