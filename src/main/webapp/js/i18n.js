$(document).ready(function() {
    var selectedItem = localStorage.getItem("locales");
    $('#locales').val(selectedItem ? selectedItem : 'en');
    $('#locales').change(function() {

        var selectedOption = $('#locales').val();
        if (selectedOption) {
            window.location.replace('?lang=' + selectedOption);
            localStorage.setItem("locales", selectedOption);

        }
    });
});