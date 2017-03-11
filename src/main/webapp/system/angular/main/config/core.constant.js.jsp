<%@page language="java" contentType="application/javascript; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String requestURL = request.getRequestURL().toString();
    String servletPath = request.getServletPath();
    String baseURL = requestURL.replace(servletPath, "");
%>

(function () {
    'use strict'

    angular
        .module('com.system.core')
        .constant('CORE_CONFIG', {
            'contextRoot': '${pageContext.request.contextPath}',
            'baseURL': '<%= baseURL %>'
        });

})();