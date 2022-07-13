<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">


<jsp:include page="head.jsp"></jsp:include>

  <body>
  <!-- Pre-loader start -->
  <jsp:include page="theme-loader.jsp"></jsp:include>
  <!-- Pre-loader end -->
  
  
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
      
      <!-- Inicio barra de navegaçao     -->
      
      <jsp:include page="navbar.jsp"></jsp:include>
         
      <!-- end navbar -->
    
          <div class="pcoded-main-container">
          
              <div class="pcoded-wrapper">
              <!-- INICIO NAVBAR pcoded-navbar -->
              
                 <jsp:include page="navbarmainmenu.jsp"></jsp:include>
                 <!-- END OF NAVBAR  --> 
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                      
                      <jsp:include page="page-header.jsp"></jsp:include>
                      
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                            
                                <div class="page-wrapper">
                                
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                    
                                        <div class="row">
                                        
                                          <h1>Conteúdo das pastas do sistemas</h1>
                                            <!--  project and team member end -->
                                        </div>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Warning Section Ends -->
    
    <!-- Required Jquery -->
   <jsp:include page="javascriptfile.jsp"></jsp:include>
</body>

</html>
    