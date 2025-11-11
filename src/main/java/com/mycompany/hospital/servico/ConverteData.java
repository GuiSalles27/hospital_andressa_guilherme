/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.servico;

/**
 *
 * @author 15114560603
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author tulio
 */
public class ConverteData {
static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Calendar cal = Calendar.getInstance();

public static Date somarData(int dias, Date data) {
Calendar calendar = Calendar.getInstance();
calendar.setTime(data);
calendar.add(Calendar.DATE, dias);
return calendar.getTime();
}

public static String dataAtual() {
return sdf.format(new Date());
}

public Calendar converteCalendario(String data) {
if (data == null || data.trim().isEmpty()) {
return null;
}
try {
cal.setTime(sdf.parse(data));
} catch (ParseException e) {
e.printStackTrace();
return null;
}
return cal;
}

public Calendar converteCalendario(Date data) {
if (data == null) {
return null;
}
Calendar dataCal = Calendar.getInstance();
dataCal.setTime(data);
return dataCal;
}

public static String getDataFormatada(Calendar data) {
if (data == null) {
return "";
}
SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
return formatar.format(data.getTime());
}

public static String converteTela(Object value) {
if (value == null) {
return "";
}
Calendar c = (Calendar) value;
return sdf.format(c.getTime());
}

public static String formataDate(Calendar calendar) {
if (calendar == null) {
return "";
}
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
return formatter.format(calendar.getTime());
}

public static String formataDate(Object value) {
if (value == null) {
return "";
}
Calendar c = (Calendar) value;
SimpleDateFormat formata = new SimpleDateFormat("yyyy-MM-dd");
return formata.format(c.getTime());
}

/**
* Converte uma data do formato dd/MM/yyyy para yyyy-MM-dd.
* Agora trata strings nulas ou vazias.
* @param dateStr A data no formato dd/MM/yyyy.
* @return A data no formato yyyy-MM-dd ou uma string vazia se a entrada for inválida.
*/
public static String convertDateFormat(String dateStr) {
if (dateStr == null || dateStr.trim().isEmpty()) {
return ""; // Retorna string vazia se a entrada for nula ou vazia
}
SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
try {
Date date = inputFormat.parse(dateStr);
return outputFormat.format(date);
} catch (ParseException e) {
// Se o formato já for o do banco, retorna o próprio valor
if (dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
return dateStr;
}
e.printStackTrace();
return ""; // Retorna vazio em caso de erro de parse
}
}

public java.sql.Date converteBanco(Object value) {
if (value == null) {
return null;
}
Calendar c = (Calendar) value;
return new java.sql.Date(c.getTimeInMillis());
}
}
