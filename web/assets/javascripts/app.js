$(document).ready(function(){

    $(".datepicker").inputmask("dd/mm/yyyy");
    $(".monthpicker").inputmask("mm/yyyy");
    $(".time").inputmask("hh:mm");
    $('.area-code').inputmask('99');
    $('.zip-code').inputmask('99.999-999');
    $('.number').inputmask({alias: 'numeric', greedy : false, rightAlign: false});
    $('.percentage').inputmask({alias: 'percentage', greedy : false, rightAlign: false});
    $('.federal-id').inputmask('999.999.999-99');
    $('.cnpj').inputmask('99.999.999/9999-99');
    $('.cellphones').inputmask('[9]9999-9999', {greedy : false, numericInput: true});
    $('.agency').inputmask('9999');
    $('.account').inputmask('99999[99999]');


  Inputmask.extendAliases({
    "currency": {
      prefix: "R$ ",
      placeHolder: '0,0',
      autoUnmask: true,
      rightAlign: false
    }
  });
   $('.currency').inputmask({alias: 'currency'});

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
