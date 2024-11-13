function submitForm() {
  document.getElementById("loader").style.display = "block";
  setTimeout(() => {
    document.getElementById("uploadForm").submit();
  }, 1000);
}