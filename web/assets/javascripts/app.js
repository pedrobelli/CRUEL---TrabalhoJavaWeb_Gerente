$(document).ready(function(){

    $(".datepicker").inputmask("dd/mm/yyyy");
    $(".monthpicker").inputmask("mm/yyyy");
    $(".time").inputmask("hh:mm");
    $('.area-code').inputmask('99');
    $('.zip-code').inputmask('99.999-999', {removeMaskOnSubmit: true});
    $('.number').inputmask({alias: 'numeric', greedy : false, rightAlign: false});
    $('.percentage').inputmask({alias: 'percentage', greedy : false, rightAlign: false});
    $('.federal-id').inputmask('999.999.999-99', {removeMaskOnSubmit: true});
    $('.cnpj').inputmask('99.999.999/9999-99');
    $('.cellphones').inputmask('(99) [9]9999-9999', {greedy : false, removeMaskOnSubmit: true});
    $('.agency').inputmask('9999');
    $('.account').inputmask('99999[99999]');
    $('.currency').inputmask('99.99', { numericInput: true });

    $('.email').inputmask({
      mask: "*{1,20}[.*{1,20}][.*{1,20}][.*{1,20}]@*{1,20}[.*{2,6}][.*{1,2}]",
      greedy: false,
      onBeforePaste: function (pastedValue, opts) {
        pastedValue = pastedValue.toLowerCase();
        return pastedValue.replace("mailto:", "");
      },
      definitions: {
        '*': {
          validator: "[0-9A-Za-z!#$%&'*+/=?^_`{|}~\-]",
          cardinality: 1,
          casing: "lower"
        }
      }
    });
 
  

});
