/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function ($) {
    $.each(['show', 'hide'], function (i, ev) {
        var el = $.fn[ev];
        $.fn[ev] = function () {
            this.triggerHandler(ev);
            return el.apply(this, arguments);
        };
    });
})(jQuery);



$(document).ready(function () {
    if (PrimeFaces.widget.SelectOneMenu) {

        PrimeFaces.widget.SelectOneMenu.prototype.callHandleMethod = function (handleMethod, event) {
            var $this = this;

            if (this.cfg.dynamic && !this.isDynamicLoaded) {
                this.dynamicPanelLoad();

                var interval = setInterval(function () {
                    if ($this.isDynamicLoaded) {
                        if (handleMethod) {
                            handleMethod.call($this, event);
                        }

                        clearInterval(interval);
                    }
                }, 10);
            } else {
                this.renderPanelContentFromHiddenSelect(true);

                if (handleMethod) {
                    handleMethod.call(this, event);
                }
            }
        };

        PrimeFaces.widget.SelectOneMenu.prototype.selectValue = function (value) {
            if (!this.items || this.items.length === 0) {
                this.callHandleMethod(null, null);
            }

            var option = this.options.filter('[value="' + $.escapeSelector(value) + '"]');

            this.selectItem(this.items.eq(option.index()), true);
        };
    }


//    if (window['PrimeFaces'].widget.Dialog) {
//        PrimeFaces.widget.Dialog = PrimeFaces.widget.Dialog.extend({
//
//            enableModality: function () {
//                this._super();
//                $(document.body).children(this.jqId + '_modal').addClass('ui-dialog-mask');
//                $('html, body').css('overflow', 'hidden');
//            },
//            disableModality: function () {
//                this._super();
//                $('html, body').css('overflow', 'auto');
//            },
//
//            syncWindowResize: function () {}
//        });
//    }

//    iniciar();
});

function show(btn) {
    console.log("Hola");
    console.log('Button id:', btn.id);
}
;
function movervalortiempo(elemento) {
//    console.log("this " + this.id + "elemento " + elemento.id);
    var nombreboton = elemento.id;
    var indexg = nombreboton.lastIndexOf("_");
    var nombrecc = nombreboton.substring(indexg, nombreboton.length);
    var indexmedida = nombreboton.lastIndexOf("-") + 1;
    var medida = nombreboton.substring(indexmedida, indexg);
    var nombreelemento = "time" + medida + "WV" + nombrecc;
//    console.log(nombreboton === "subir-" + medida + nombrecc);
//    console.log("index " + indexg + " Nombre extraido " + nombrecc + " medida" + medida
//            + " nomnreelemento" + nombreelemento);
    var valor = +PF(nombreelemento).getSelectedValue();
    var maxval = 12;
    var inicial = 1;
    //var incrementos = 1;


    if (nombreelemento === 'timehoraWV' + nombrecc) {
        maxval = 12;
        inicial = 1;
    } else if (nombreelemento === 'timeminutosWV' + nombrecc) {
        maxval = 59;
        inicial = 0;
    }
    if (nombreboton === "bajar-" + medida + nombrecc) {

        if (valor > inicial) {
            valor = +valor - 1;
        } else {
            valor = +maxval;
        }

    } else {
        if (valor < maxval) {
            valor = +valor + 1;
        } else {
            valor = +inicial;
        }
    }
    PF(nombreelemento).selectValue(valor);
}
;

function cambiarampm(elemento) {
    var nombreboton = elemento.id;
    var indexg = nombreboton.lastIndexOf("_");
    var nombrecc = nombreboton.substring(indexg, nombreboton.length);
    var nombreelemento = "timeampmWV" + nombrecc;
//    console.log(nombreelemento);
    var valor = PF(nombreelemento).getSelectedValue();
    if (valor === "AM") {
        PF(nombreelemento).selectValue("PM");
    } else {
        PF(nombreelemento).selectValue("AM");
    }

}
;

function guardartiempo(elemento) {
    var nombreboton = elemento.id;
    var indexg = nombreboton.lastIndexOf("_");
    var nombrecc = nombreboton.substring(indexg, nombreboton.length);
    var hora = PF("timehoraWV" + nombrecc).getSelectedValue();
    var minutos = PF("timeminutosWV" + nombrecc).getSelectedValue();
    var ampm = PF("timeampmWV" + nombrecc).getSelectedValue();
    if (+hora < 10) {
        hora = "0" + hora;
    }
    if (+minutos < 10) {
        minutos = "0" + minutos;
    }
//    console.log("La hora es " + hora + ":" + minutos + " " + ampm);
//    console.log("Nombre del item " + "#text" + nombrecc);
    $("#text" + nombrecc).val(hora + ":" + minutos + " " + ampm);
    PF('inputtiempo' + nombrecc).jq.val(hora + ":" + minutos + " " + ampm);
    PF('dialogotiempoinputwv' + nombrecc).hide();


}
;
function cancelartiempo(elemento) {
    var nombreboton = elemento.id;
    var indexg = nombreboton.lastIndexOf("_");
    var nombrecc = nombreboton.substring(indexg, nombreboton.length);
    PF('dialogotiempoinputwv' + nombrecc).hide();
}
;
function iniciar() {
    $(".dialogotiempo").on('show', function () {
        if (!$(this).attr('wasshowed')) {
            console.log("Aca");
            $(this).attr('wasshowed', "true");
            try {
                var nombredialogo = this.id;
                var indexg = nombredialogo.lastIndexOf("_");
                var nombrecc = nombredialogo.substring(indexg, nombredialogo.length);
                var valoractual = PF('inputtiempo' + nombrecc).jq.val();
                console.log(valoractual);
//                var hora = PF("timehoraWV" + nombrecc).getSelectedValue();
//                var minutos = PF("timeminutosWV" + nombrecc).getSelectedValue();
//                var ampm = PF("timeampmWV" + nombrecc).getSelectedValue();

            } catch (e) {
                console.log("Error " + e);
            }

            $(".botonguardar").click(function () {
                console.log("Aqui");
                guardartiempo(this);
            });
            $(".timeminutoshoras").on('mousedown touchstart', function (e) {
                if (e.which !== 2 && e.which !== 3) {
                    var elemento = this;
                    timeOut = setInterval(function () {
                        movervalortiempo(elemento);
                    }, 100);
                }
            }).bind('mouseup mouseleave touchend', function (e) {
//                console.log(timeOut);
                clearInterval(timeOut);
            });


            $(".timeampm").click(function () {
                cambiarampm(this);
            });


        }
//        console.log("Se ejecutodialogo");
    });
}
;

function ovBodyAuto(){
    $('html, body').css('overflow', 'auto');
}

function ovBodyHidden(){
    $('html, body').css('overflow', 'hidden');
}

function almostrar(dialogo) {
//    var iddialogo = $(".dialogotiempo");

    var iddialogo = dialogo.jq;
//    console.log("Aca" + iddialogo);
    try {
        var nombredialogo = iddialogo.attr('id');
        var indexg = nombredialogo.lastIndexOf("_");
        var nombrecc = nombredialogo.substring(indexg, nombredialogo.length);
        var valoractual = PF('inputtiempo' + nombrecc).jq.val();
        var horaactual = new Date();
        var hora = horaactual.getHours();
        var minutos = +horaactual.getMinutes();
        var ampm = hora >= 12 ? 'PM' : 'AM';
        hora = +hora % 12;
        hora = hora ? +hora : 12;

        console.log(horaactual.toLocaleString('es-PA', {hour: 'numeric', minute: 'numeric', hour12: true}));
        if (valoractual !== "") {
            var indexminutos = valoractual.lastIndexOf(":");
            var indexampm = valoractual.lastIndexOf(' ');
            if (indexminutos > -1 && indexampm > -1) {
                ampm = valoractual.substring(indexampm + 1, valoractual.length);
                hora = +valoractual.substring(0, indexminutos);
                minutos = +valoractual.substring(indexminutos + 1, indexampm);
            }
        }
        PF("timeampmWV" + nombrecc).selectValue(ampm);
        PF("timehoraWV" + nombrecc).selectValue(hora);
        PF("timeminutosWV" + nombrecc).selectValue(minutos);
    } catch (e) {
        console.log("Error " + e);
    }
    if (!iddialogo.attr('wasshowed')) {
        iddialogo.attr('wasshowed', "true");


        $("#" + nombredialogo.replace(/:/g, "\\:") + " .botonguardar").click(function () {
            guardartiempo(this);
        });
        $("#" + nombredialogo.replace(/:/g, "\\:") + " .botoncancelar").click(function () {

            cancelartiempo(this);
        });
        $("#" + nombredialogo.replace(/:/g, "\\:") + " .timeminutoshoras").on('mousedown touchstart', function (e) {
            if (e.which !== 2 && e.which !== 3) {
                var elemento = this;
                timeOut = setInterval(function () {
                    movervalortiempo(elemento);
                }, 100);
            }
        }).bind('mouseup mouseleave touchend', function (e) {
//                console.log(timeOut);
            clearInterval(timeOut);
        }).bind('contextmenu', function (e) {
            e.preventDefault();
            e.stopPropagation();
            return false;
        });
        $("#" + nombredialogo.replace(/:/g, "\\:") + " .timeminutoshoras").each(function () {
            this.onselectstart = function () {
                return false;
            };
            this.unselectable = "on";
            $(this).css('-moz-user-select', 'none');
            $(this).css('-webkit-user-select', 'none');
        });


        $("#" + nombredialogo.replace(/:/g, "\\:") + " .timeampm").click(function () {
            cambiarampm(this);
        });


    }
//    ovBodyHidden();
}
;