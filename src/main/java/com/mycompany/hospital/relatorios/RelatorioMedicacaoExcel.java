package com.mycompany.hospital.relatorios;

import com.mycompany.hospital.dao.entidade.Medicacao;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class RelatorioMedicacaoExcel {

    private List<Medicacao> medicacoes;

    public RelatorioMedicacaoExcel(List<Medicacao> medicacoes) {
        this.medicacoes = medicacoes;
    }

    public void gerarRelatorio(HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Relatório de Medicações");

        // Cabeçalho
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Código");
        header.createCell(1).setCellValue("Nome");
        header.createCell(2).setCellValue("Tipo");
        header.createCell(3).setCellValue("Valor (R$)");
        header.createCell(4).setCellValue("Fabricação");
        header.createCell(5).setCellValue("Desconto (%)");

        int rowCount = 1;
        for (Medicacao m : medicacoes) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(m.getCodRemedio());
            row.createCell(1).setCellValue(m.getNomeRemedio());
            row.createCell(2).setCellValue(m.getTipoRemedio());
            row.createCell(3).setCellValue(m.getValorRemedio());
            row.createCell(4).setCellValue(m.getFabricacao());
            row.createCell(5).setCellValue(m.getDesconto() == null ? 0 : m.getDesconto());
        }

        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        // Download
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio_medicacao.xlsx");

        try (ServletOutputStream out = response.getOutputStream()) {
            workbook.write(out);
        }
        workbook.close();
    }
}
