package com.mycompany.hospital.relatorios;

import com.mycompany.hospital.dao.entidade.Exame;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RelatorioExameExcel {

    public void exportarExames(List<Exame> exames, OutputStream out) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Exames");

            // Cabeçalho
            Row header = sheet.createRow(0);
            String[] colunas = {"Código", "Tipo Exame", "Valor", "Resultado", "Paciente", "Funcionário"};

            for (int i = 0; i < colunas.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(colunas[i]);
            }

            // Linhas
            int rowNum = 1;
            for (Exame e : exames) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(e.getCodExame());
                row.createCell(1).setCellValue(e.getTipoExame());
                row.createCell(2).setCellValue(e.getValor());
                row.createCell(3).setCellValue(e.getResultado());
                row.createCell(4).setCellValue(e.getPacienteExame().getNomePaciente());
                row.createCell(5).setCellValue(e.getFuncionarioExame().getNomeFunc());
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
