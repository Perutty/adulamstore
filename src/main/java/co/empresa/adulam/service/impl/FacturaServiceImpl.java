package co.empresa.adulam.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.empresa.adulam.commands.GenericServiceImpl;
import co.empresa.adulam.model.Factura;
import co.empresa.adulam.repository.FacturaRepository;
import co.empresa.adulam.services.FacturaService;

@Service
public class FacturaServiceImpl extends GenericServiceImpl<Factura, Integer> implements FacturaService{
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	@Override
	public CrudRepository<Factura, Integer> getDao(){
		return facturaRepository;
	}

	public ByteArrayInputStream exportAllData(int id) throws Exception {
		String [] columns = {"#Factura", "Fecha Creación", "Cliente", "Total a pagar"};
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Sheet sheet = workbook.createSheet("Factura");
		Row row = sheet.createRow(0);
		for(int i=0; i<columns.length;i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
		}
		
	    Factura factura = this.get(id);
		int initRow = 1;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String fechaCreacion = sdf.format(factura.getFechacreacion());
			row = sheet.createRow(initRow);
			row.createCell(0).setCellValue(factura.getId());
			row.createCell(1).setCellValue(fechaCreacion);
			row.createCell(2).setCellValue(factura.getId_cliente());
			row.createCell(3).setCellValue(factura.getTotalapagar());
			initRow++;
		
		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}
}
