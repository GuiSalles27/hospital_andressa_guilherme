package com.mycompany.hospital.relatorios;

import com.mycompany.hospital.dao.CargoDAO;
import com.mycompany.hospital.dao.entidade.Cargo;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RelatorioCargoExcel {

    private final CargoDAO cargoDAO = new CargoDAO();

    /**
     * Gera um arquivo .xlsx contendo os cargos cuja faixa salarial
     * esteja entre salarioMin e salarioMax (inclusive) e escreve no response.
     */
    public void gerarRelatorio(Double salarioMin, Double salarioMax, HttpServletResponse response) throws IOException {
        // Proteção: evite NPE (o formulário marca required, mas só por precaução)
        if (salarioMin == null) salarioMin = Double.MIN_VALUE;
        if (salarioMax == null) salarioMax = Double.MAX_VALUE;

        List<Cargo> cargos = cargoDAO.buscarPorFaixaSalarial(salarioMin, salarioMax);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Cargos");

            // Estilo do cabeçalho
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            // Cabeçalho
            Row header = sheet.createRow(0);
            String[] headers = {"Código", "Nome do Cargo", "Salário Inicial", "Descrição", "Bonificação"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Linhas de dados
            int rowIdx = 1;
            for (Cargo cargo : cargos) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(cargo.getCodCargo());
                row.createCell(1).setCellValue(cargo.getNomeCargo() != null ? cargo.getNomeCargo() : "");
                Cell salaryCell = row.createCell(2);
                salaryCell.setCellValue(cargo.getSalarioInicial());
                row.createCell(3).setCellValue(cargo.getDescricao() != null ? cargo.getDescricao() : "");
                Cell bonCell = row.createCell(4);
                bonCell.setCellValue(cargo.getBonificacao());
            }

            // Ajusta larguras
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Configura resposta para download
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_cargos.xlsx\"");

            try (ServletOutputStream out = response.getOutputStream()) {
                workbook.write(out);
                out.flush();
            }
        }
    }
}
