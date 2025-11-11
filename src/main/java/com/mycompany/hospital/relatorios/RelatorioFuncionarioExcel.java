package com.mycompany.hospital.relatorios;

import com.mycompany.hospital.dao.entidade.Funcionario;
import jakarta.servlet.ServletOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RelatorioFuncionarioExcel {

    private List<Funcionario> funcionarios;

    public RelatorioFuncionarioExcel(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void gerarRelatorio(HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Funcionários");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Nome");
        header.createCell(2).setCellValue("CPF");
        header.createCell(3).setCellValue("RG");
        header.createCell(4).setCellValue("Registro");
        header.createCell(5).setCellValue("Data Admissão");
        header.createCell(6).setCellValue("Horário");
        header.createCell(7).setCellValue("Cargo");
        header.createCell(8).setCellValue("Salário Inicial");
        header.createCell(9).setCellValue("Hospital");
        header.createCell(10).setCellValue("Cidade");
        header.createCell(11).setCellValue("Bairro");

        int rowCount = 1;
        for (Funcionario f : funcionarios) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(f.getCodFunc());
            row.createCell(1).setCellValue(f.getNomeFunc());
            row.createCell(2).setCellValue(f.getCpf());
            row.createCell(3).setCellValue(f.getRg());
            row.createCell(4).setCellValue(f.getNumRegistro());
            row.createCell(5).setCellValue(f.getDataAdmissao() != null ? f.getDataAdmissao().getTime().toString() : "");
            row.createCell(6).setCellValue(f.getHorarioTrabalho());
            row.createCell(7).setCellValue(f.getCargoFuncionario() != null ? f.getCargoFuncionario().getNomeCargo() : "—");
            row.createCell(8).setCellValue(f.getCargoFuncionario() != null ? f.getCargoFuncionario().getSalarioInicial() : 0.0);
            row.createCell(9).setCellValue(f.getHospitalFuncionario() != null ? f.getHospitalFuncionario().getCodHospital() : 0);
            row.createCell(10).setCellValue(f.getHospitalFuncionario() != null ? f.getHospitalFuncionario().getCidade() : "—");
            row.createCell(11).setCellValue(f.getHospitalFuncionario() != null ? f.getHospitalFuncionario().getBairro() : "—");
        }

        for (int i = 0; i <= 11; i++) {
            sheet.autoSizeColumn(i);
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio_funcionarios.xlsx");

        try (ServletOutputStream os = response.getOutputStream()) {
            workbook.write(os);
        }
        workbook.close();
    }
}
