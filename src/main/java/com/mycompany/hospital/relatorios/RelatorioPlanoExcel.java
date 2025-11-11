package com.mycompany.hospital.relatorios;

import com.mycompany.hospital.dao.entidade.Plano;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RelatorioPlanoExcel {

    public void exportarPlanos(List<Plano> planos, OutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Planos de Saúde");

        // Cabeçalho
        Row headerRow = sheet.createRow(0);
        String[] colunas = {"Código", "Nome", "Tipo", "Valor"};
        for (int i = 0; i < colunas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(colunas[i]);
        }

        // Linhas
        int rowNum = 1;
        for (Plano p : planos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(p.getCodPlano());
            row.createCell(1).setCellValue(p.getNomePlano());
            row.createCell(2).setCellValue(p.getTipoPlano());
            row.createCell(3).setCellValue(p.getValorPlano());
        }

        // Ajuste de largura das colunas
        for (int i = 0; i < colunas.length; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(outputStream);
        workbook.close();
    }
}
