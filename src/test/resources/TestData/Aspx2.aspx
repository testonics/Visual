<%@ Page Title="Home Page" Language34="C#" AutoEventWireup="true"   
CodeBehind="Default.aspx.cs" Inherits="FileDownloadExample._Default" %>  
<form id="form1" runat="server">  
    <p>  
        Click the button to download a file</p>  
    <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" Text="Download" />  
    <br />  
    <br />  
    <asp:Label ID="Label1" runat="server"></asp:Label>  
</form>  