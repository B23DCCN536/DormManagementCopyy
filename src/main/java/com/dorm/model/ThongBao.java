/*    */ package com.dorm.model;
/*    */ 
/*    */ import java.time.LocalDate;
/*    */ 
/*    */ public class ThongBao
/*    */ {
/*    */   private String maThongBao;
/*    */   private String tieuDe;
/*    */   private String noiDung;
/*    */   private LocalDate ngayDang;
/*    */   
/*    */   public ThongBao() {}
/*    */   
/*    */   public ThongBao(String maThongBao, String tieuDe, String noiDung, LocalDate ngayDang) {
/* 15 */     this.maThongBao = maThongBao;
/* 16 */     this.tieuDe = tieuDe;
/* 17 */     this.noiDung = noiDung;
/* 18 */     this.ngayDang = ngayDang;
/*    */   }
/*    */ 
/*    */   
/*    */   public void guiThongBao() {}
/*    */ 
/*    */   
/*    */   public String getMaThongBao() {
/* 26 */     return this.maThongBao;
/*    */   }
/*    */   
/*    */   public void setMaThongBao(String maThongBao) {
/* 30 */     this.maThongBao = maThongBao;
/*    */   }
/*    */   
/*    */   public String getTieuDe() {
/* 34 */     return this.tieuDe;
/*    */   }
/*    */   
/*    */   public void setTieuDe(String tieuDe) {
/* 38 */     this.tieuDe = tieuDe;
/*    */   }
/*    */   
/*    */   public String getNoiDung() {
/* 42 */     return this.noiDung;
/*    */   }
/*    */   
/*    */   public void setNoiDung(String noiDung) {
/* 46 */     this.noiDung = noiDung;
/*    */   }
/*    */   
/*    */   public LocalDate getNgayDang() {
/* 50 */     return this.ngayDang;
/*    */   }
/*    */   
/*    */   public void setNgayDang(LocalDate ngayDang) {
/* 54 */     this.ngayDang = ngayDang;
/*    */   }
/*    */ }


/* Location:              D:\DormManagement\a.jar!\src\main\java\com\dorm\model\ThongBao.class
 * Java compiler version: 24 (68.0)
 * JD-Core Version:       1.1.3
 */