package presenter;

import model.Lab;
import model.NonExistentLabException;
import model.Student;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Exporter {
    public static void export(String path, String labID, String[] taNames) throws NonExistentLabException {
        Lab l = LabManager.getLabById(labID);

        if (l != null) {
            List<Student> s = l.getStudents();

            generateRoster(path, labID, s, taNames);
        }

        else
            throw new NonExistentLabException("Lab " + labID + " does not exist");
    }

    private static void generateRoster(String path, String labID, List<Student> students, String[] taList) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Roster");

        createTitle(sheet, workbook, labID);

        createHeader(sheet, workbook);

        populateStudents(workbook, sheet, students);

        populateTAList(workbook, sheet, 2 + students.size() + 1, taList);

        sheet.setColumnWidth(0, 900);
        for (int i = 1; i <= 9; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private static void createTitle(HSSFSheet sheet, HSSFWorkbook workbook, String labID) {
        sheet.setDefaultRowHeightInPoints(20);


        // TITLE ROW
        HSSFRow title = sheet.createRow(0);
        HSSFRichTextString titleString = new HSSFRichTextString("CPSC 110 " + labID);

        title.setHeightInPoints(25);

        Font titleFont = workbook.createFont();

        titleFont.setBold(true);
        titleFont.setFontName(HSSFFont.FONT_ARIAL);
        titleFont.setFontHeightInPoints((short) 20);

        titleString.applyFont(titleFont);

        title.createCell(0).setCellValue(titleString);

        HSSFRichTextString labString = new HSSFRichTextString("Lab: _______________");

        Font labFont = workbook.createFont();
        labFont.setFontName(HSSFFont.FONT_ARIAL);
        labFont.setFontHeightInPoints((short) 20);
        labString.applyFont(labFont);

        title.createCell(9).setCellValue(labString);
    }

    private static void createHeader(HSSFSheet sheet, HSSFWorkbook workbook) {

        HSSFCellStyle headerStyle = workbook.createCellStyle();
        HSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        headerStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());

        // HEADER ROW
        HSSFRow header = sheet.createRow(1);

        HSSFRichTextString fname = new HSSFRichTextString("First Name");
        HSSFRichTextString lname = new HSSFRichTextString("Last Name");
        HSSFRichTextString sn = new HSSFRichTextString("Student Number");
        HSSFRichTextString pl = new HSSFRichTextString("Pre-Lab (/2)");
        HSSFRichTextString att = new HSSFRichTextString("Attendance (/1)");
        HSSFRichTextString lq = new HSSFRichTextString("Lab Quiz (/1)");
        HSSFRichTextString bh = new HSSFRichTextString("Behaviour (/1)");
        HSSFRichTextString total = new HSSFRichTextString("Total (/5)");
        HSSFRichTextString remarks = new HSSFRichTextString("Remarks                                                  ");

        fname.applyFont(headerFont);
        lname.applyFont(headerFont);
        sn.applyFont(headerFont);
        pl.applyFont(headerFont);
        att.applyFont(headerFont);
        lq.applyFont(headerFont);
        bh.applyFont(headerFont);
        total.applyFont(headerFont);
        remarks.applyFont(headerFont);

        header.createCell(1).setCellValue(lname);
        header.createCell(2).setCellValue(fname);
        header.createCell(3).setCellValue(sn);
        header.createCell(4).setCellValue(pl);
        header.createCell(5).setCellValue(att);
        header.createCell(6).setCellValue(lq);
        header.createCell(7).setCellValue(bh);
        header.createCell(8).setCellValue(total);
        header.createCell(9).setCellValue(remarks);

        for (int i = 1; i <=9; i++) {
            header.getCell(i).setCellStyle(headerStyle);
        }

    }

    private static void populateStudents(HSSFWorkbook workbook, HSSFSheet sheet, List<Student> students) {
        // POPULATE ROW WITH STUDENT DATA

        int studCount = 1;
        int rowCount = 2;

        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);

            HSSFRow row = sheet.createRow(rowCount);

            row.createCell(0).setCellValue(studCount);
            row.createCell(1).setCellValue(s.getLname());
            row.createCell(2).setCellValue(s.getFname());
            row.createCell(3).setCellValue(s.getSID());

            for (int j = 4; j <= 9; j++) {
                row.createCell(j);
                row.getCell(j).setCellStyle(style);
                row.getCell(j - 3).setCellStyle(style);
            }

            studCount++;
            rowCount++;
        }
    }


    private static void populateTAList(HSSFWorkbook workbook, HSSFSheet sheet, int start, String[] taNames) {

        HSSFRow row = sheet.createRow(start);

        HSSFCellStyle cs = workbook.createCellStyle();

        cs.setAlignment(HorizontalAlignment.RIGHT);

        HSSFRichTextString lbl_ta = new HSSFRichTextString("TAs:");
        Font taFont = workbook.createFont();

        taFont.setBold(true);

        lbl_ta.applyFont(taFont);

        HSSFCell tas_label_cell =  row.createCell(1);

        tas_label_cell.setCellStyle(cs);

        tas_label_cell.setCellValue(lbl_ta);

        row.createCell(2).setCellValue(taNames[0]);

        for (int i = 1; i < 3; i++) {
            row = sheet.createRow(start + i);

            row.createCell(2).setCellValue(taNames[i]);
        }
    }
}
