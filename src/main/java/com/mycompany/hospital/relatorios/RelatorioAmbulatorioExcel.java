package com.mycompany.hospital.relatorios;

import com.mycompany.hospital.dao.entidade.Ambulatorio;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RelatorioAmbulatorioExcel {

    public void exportarAmbulatorios(List<Ambulatorio> ambulatorios, OutputStream out) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Ambulatórios");

            // Cabeçalho
            Row header = sheet.createRow(0);
            String[] colunas = {"Código", "Horário Atendimento", "Qtd Ambulâncias", "Hospital"};

            for (int i = 0; i < colunas.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(colunas[i]);
            }

            // Linhas
            int rowNum = 1;
            for (Ambulatorio a : ambulatorios) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(a.getCodAmbulatorio());
                row.createCell(1).setCellValue(a.getHorarioAtendimento());
                row.createCell(2).setCellValue(a.getQuantAmbulancias());
                row.createCell(3).setCellValue(a.getHospitalAmbulatorio().getDescricao());
            }

            for (int i = 0; i < colunas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}