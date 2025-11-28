package com.dorm.util;
/*    */ 
/*    */ import com.dorm.model.TaiKhoan;
/*    */ 
/*    */ 
/*    */ public class Session
/*    */ {
/*    */   private static com.dorm.util.Session instance;
/*    */   private TaiKhoan currentUser;
/*    */   
/*    */   public static com.dorm.util.Session getInstance() {
/* 12 */     if (instance == null) {
/* 13 */       instance = new com.dorm.util.Session();
/*    */     }
/* 15 */     return instance;
/*    */   }
/*    */   
/*    */   public void setCurrentUser(TaiKhoan user) {
/* 19 */     this.currentUser = user;
/*    */   }
/*    */   
/*    */   public TaiKhoan getCurrentUser() {
/* 23 */     return this.currentUser;
/*    */   }
/*    */   
/*    */   public void logout() {
/* 27 */     this.currentUser = null;
/*    */   }
/*    */ }


/* Location:              D:\DormManagement\a.jar!\src\main\java\com\dor\\util\Session.class
 * Java compiler version: 24 (68.0)
 * JD-Core Version:       1.1.3
 */