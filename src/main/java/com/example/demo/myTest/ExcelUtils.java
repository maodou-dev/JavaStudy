//package com.example.demo.myTest;
//
///**
// * Excel解析工具类
// *
// * @author yangjie
// * @mail yangjie@yinhai.com
// * @date 2021/3/1
// * @time 20:12
// * @since 1.0.0
// */
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.http.MediaType;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.math.BigDecimal;
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//
//public class ExcelUtils {
//
//    private final static Logger log = LoggerFactory.getLogger(cn.hsa.pss.hall.util.ExcelUtils.class);
//
//    private final static String EXCEL2003 = "xls";
//    private final static String EXCEL2007 = "xlsx";
//
//    /**
//     * @param clsSource 网厅层的实体类
//     * @param clsTarget 最终封装并返回的实体类文件
//     * @param file      上传的Excel文件
//     * @param startRow  从第几行开始读 从0开始计数;
//     * @description: 读取Excel中的数据并封装到实体类中
//     */
//    public static <T> List<T> readExcel(Class<?> clsSource, Class<T> clsTarget, MultipartFile file, Integer startRow) {
//
//        String fileName = file.getOriginalFilename();
//        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
//            log.error("上传文件格式不正确");
//            return null;
//        }
//        List<T> dataList = new ArrayList<>();
//        Workbook workbook = null;
//        try {
//            InputStream is = file.getInputStream();
//            if (fileName.endsWith(EXCEL2007)) {
//                workbook = new XSSFWorkbook(is);
//            }
//            if (fileName.endsWith(EXCEL2003)) {
//                workbook = new HSSFWorkbook(is);
//            }
//            if (workbook != null) {
//                //类映射  注解 value-->bean columns
//                Map<String, Field> classMap = new HashMap<>(16); //{"出生日期"："brdy"}
//                List<Field> fields = Stream.of(clsSource.getDeclaredFields()).collect(Collectors.toList());
//                fields.forEach(
//                        field -> {
//                            cn.hsa.pss.hall.util.ExcelColumn annotation = field.getAnnotation(cn.hsa.pss.hall.util.ExcelColumn.class);
//                            if (annotation != null) {
//                                String value = new Integer(annotation.col()).toString();
//                                if (StringUtils.isBlank(value)) {
//                                    return;//return起到的作用和continue是相同的 语法
//                                }
//                                if (!classMap.containsKey(value)) {
//                                    field.setAccessible(true);
//                                    classMap.put(value, field);
//                                }
//
//                            }
//                        }
//                );
//                //默认读取第一个sheet
//                Sheet sheet = workbook.getSheetAt(0);
//                for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
//                    Row row = sheet.getRow(i);
//                    //忽略空白行
//                    if (row == null) {
//                        continue;
//                    }
//                    try {
//                        Object o = clsSource.newInstance();
//                        T t = clsTarget.newInstance();
//                        Set<String> keys = classMap.keySet();
//                        //判断是否为空白行
//                        boolean allBlank = false;
//                        Iterator<String> iterator = keys.iterator();
//                        while (iterator.hasNext()) {
//                            String col = iterator.next();
//                            Field field = classMap.get(col);
//                            int j = Integer.parseInt(col);
//                            Cell cell = row.getCell(j);
//                            String cellValue = getCellValue(cell);
//                            handleField(o, cellValue, field);
//                        }
//                        if (!allBlank) {
//                            BeanUtils.copyProperties(o, t);
//                            dataList.add(t);
//                        } else {
//                            log.warn(String.format("row:%s is blank ignore!", i));
//                        }
//                    } catch (Exception e) {
//                        log.error(String.format("parse row:%s exception!", i), e);
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            log.error(String.format("parse excel exception!"), e);
//        } finally {
//            if (workbook != null) {
//                try {
//                    workbook.close();
//                } catch (Exception e) {
//                    log.error(String.format("parse excel exception!"), e);
//                }
//            }
//        }
//        return dataList;
//    }
//
//    private static <T> void handleField(T t, String value, Field field) throws Exception {
//        Class<?> type = field.getType();
//        if (type == null || type == void.class || StringUtils.isBlank(value)) {
//            return;
//        }
//        if (type == Object.class) {
//            field.set(t, value);
//            //数字类型
//        } else if (type.getSuperclass() == null || type.getSuperclass() == Number.class) {
//            if (type == int.class || type == Integer.class) {
//                field.set(t, NumberUtils.toInt(value));
//            } else if (type == long.class || type == Long.class) {
//                field.set(t, NumberUtils.toLong(value));
//            } else if (type == byte.class || type == Byte.class) {
//                field.set(t, NumberUtils.toByte(value));
//            } else if (type == short.class || type == Short.class) {
//                field.set(t, NumberUtils.toShort(value));
//            } else if (type == double.class || type == Double.class) {
//                field.set(t, NumberUtils.toDouble(value));
//            } else if (type == float.class || type == Float.class) {
//                field.set(t, NumberUtils.toFloat(value));
//            } else if (type == char.class || type == Character.class) {
//                field.set(t, CharUtils.toChar(value));
//            } else if (type == boolean.class) {
//                field.set(t, BooleanUtils.toBoolean(value));
//            } else if (type == BigDecimal.class) {
//                field.set(t, new BigDecimal(value));
//            }
//        } else if (type == Boolean.class) {
//            field.set(t, BooleanUtils.toBoolean(value));
//
//        } else if (type == Date.class) {
//            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(value);
//            field.set(t, date);
//        } else if (type == String.class) {
//            field.set(t, value);
//        } else {
//            Constructor<?> constructor = type.getConstructor(String.class);
//            field.set(t, constructor.newInstance(value));
//        }
//    }
//
//    private static String getCellValue(Cell cell) {
//        if (cell == null) {
//            return "";
//        }
//        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//            if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                return HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
//            } else {
//                return new BigDecimal(cell.getNumericCellValue()).toString();
//            }
//        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
//            return StringUtils.trimToEmpty(cell.getStringCellValue());
//        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
//            return StringUtils.trimToEmpty(cell.getCellFormula());
//        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
//            return "";
//        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
//            return String.valueOf(cell.getBooleanCellValue());
//        } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
//            return "ERROR";
//        } else {
//            return cell.toString().trim();
//        }
//
//    }
//
//    /**
//     * @param fileName     待下载的数据文件
//     * @param response 响应对象
//     * @param dataList 待组装的数据,如果没有数据,传null ;
//     * @param cls      与报盘对应的实体类字节码;
//     * @description: 下载Excel
//     */
//    public static <T> void writeExcel(String fileName , HttpServletResponse response, List<T> dataList, Class<T> cls) throws Exception {
//        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
//        Workbook workbook = null;
//        if (EXCEL2007.equals(suffix)) {
//            ClassPathResource classPathResource = new ClassPathResource("excelTpl"+File.separatorChar+fileName);
//            InputStream inputStream = classPathResource.getInputStream();
//            workbook = new XSSFWorkbook(inputStream);
//        }
//        if (EXCEL2003.equals(suffix)) {
//            ClassPathResource classPathResource = new ClassPathResource("excelTpl"+File.separatorChar+fileName);
//            InputStream inputStream = classPathResource.getInputStream();
//            workbook  = new HSSFWorkbook(inputStream);
//        }
//
//        Sheet sheet = workbook.getSheet("Sheet1");
//        AtomicInteger ai = new AtomicInteger(1);
//        if (CollectionUtils.isNotEmpty(dataList)) {
//            Field[] fields = cls.getDeclaredFields();
//            List<Field> fieldList = Arrays.stream(fields)
//                    .filter(field -> {
//                        cn.hsa.pss.hall.util.ExcelColumn annotation = field.getAnnotation(cn.hsa.pss.hall.util.ExcelColumn.class);
//                        if (annotation != null && annotation.col() >= 0) {
//                            field.setAccessible(true);
//                            return true;
//                        }
//                        return false;
//                    }).sorted(Comparator.comparing(field -> {
//                        int col = 0;
//                        cn.hsa.pss.hall.util.ExcelColumn annotation = field.getAnnotation(cn.hsa.pss.hall.util.ExcelColumn.class);
//                        if (annotation != null) {
//                            col = annotation.col();
//                        }
//                        return col;
//                    })).collect(Collectors.toList());
//            dataList.forEach(t -> {
//                Row row1 = sheet.createRow(ai.getAndIncrement());
//                AtomicInteger aj = new AtomicInteger();
//                fieldList.forEach(field -> {
//                    Class<?> type = field.getType();
//                    Object value = "";
//                    try {
//                        value = field.get(t);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    Cell cell = row1.createCell(aj.getAndIncrement());
//                    if (value != null) {
//                        if (type == Date.class) {
//                            cell.setCellValue(value.toString());
//                        } else {
//                            cell.setCellValue(value.toString());
//                        }
//                        cell.setCellValue(value.toString());
//                    }
//                });
//            });
//        }
//        String prefix = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
//
//        buildExcelDocument(prefix + "."+suffix, workbook, response);   //浏览器下载excel
//    }
//
//    /**
//     * 浏览器下载excel
//     *
//     * @param fileName
//     * @param wb
//     * @param response
//     */
//
//    private static void buildExcelDocument(String fileName, Workbook wb, HttpServletResponse response) {
//        try {
//            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
//            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
//            response.flushBuffer();
//            wb.write(response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
