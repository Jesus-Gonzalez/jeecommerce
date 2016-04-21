var app = angular.module('appProyectoFinal', ['textAngular']);

app.config(function($provide) {
  $provide.decorator('taTranslations', function($delegate) {
    $delegate.html.tooltip = 'Cambiar entre html / texto rico';
    $delegate.justifyLeft.tooltip = 'Alinear a la izquierda';
    $delegate.justifyCenter.tooltip = 'Centrar';
    $delegate.justifyRight.tooltip = 'Alinear a la derecha';
    $delegate.justifyFull.tooltip = 'Justificar texto';
    $delegate.bold.tooltip = 'Negrita';
    $delegate.italic.tooltip = 'Cursiva';
    $delegate.underline.tooltip = 'Subrayar';
    $delegate.strikeThrough.tooltip = 'Rayar';
    $delegate.insertLink.tooltip = 'Insertar Enlace';
    $delegate.insertLink.dialogPrompt = "Introduzca URL del enlace";
    $delegate.editLink.targetToggle.buttontext = "Abrir en nueva ventana";
    $delegate.editLink.reLinkButton.tooltip = "Reenlazar";
    $delegate.editLink.unLinkButton.tooltip = "Desenlazar";
    $delegate.insertVideo.tooltip = 'Insertar vídeo';
    $delegate.insertVideo.dialogPrompt = 'Escriba la URL del vídeo de Youtube';
    $delegate.clear.tooltip = 'Limpiar formateado';
    $delegate.wordcount.tooltip = 'Mostrar palabras contadas';
    $delegate.charcount.tooltip = 'Mostrar caracteres contados';
    $delegate.insertImage.tooltip = 'Insertar Imagen';
    $delegate.insertImage.dialogPrompt = 'Introduzca la URL a la imagen';
    $delegate.indent.tooltip = 'Incrementar indentado';
    $delegate.outdent.tooltip = 'Decrementar indentado';
    $delegate.heading.tooltip = 'Cabecera ';
    $delegate.p.tooltip = 'Párrafo';
    $delegate.pre.tooltip = 'Texto preformateado';
    $delegate.ul.tooltip = 'Lista desordenada';
    $delegate.quote.tooltip = 'Citar';
    $delegate.undo.tooltip = 'Deshacer';
    $delegate.redo.tooltip = 'Rehacer';

    return $delegate;
  });
})