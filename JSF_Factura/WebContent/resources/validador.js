function validarEnteros(evt) {
	keynum = (document.all) ? evt.keyCode : evt.which;
	// ESPACIO=32
	// BORRAR=8
	// ENTER=13
	//alert("keynum " + keynum);
	if ((keynum > 47 && keynum < 58) || keynum == 8 || keynum == 13) {
		return true;
	} else {
		return false;
	}
}

function validarDecimales(e, field) {
	  key = e.keyCode ? e.keyCode : e.which;
	  // backspace
	  if (key == 8) return true;
	  // 0-9
	  if (key > 47 && key < 58) {
	    if (field.value == "") return true;
	    regexp = /.[0-9]{2}$/;
	    return !(regexp.test(field.value));
	  }
	  // .
	  if (key == 46) {
	    if (field.value == "") return false;
	    regexp = /^[0-9]+$/;
	    return regexp.test(field.value);
	  }
	  // other key
	  return false;
	 
	}

function validarNombres(evt) {
	keynum = (document.all) ? evt.keyCode : evt.which;
	// ESPACIO=32
	// BORRAR=8
	// ENTER=13
	// alert("keynum "+keynum);
	// digitos
	if (!(keynum > 47 && keynum < 58) || keynum == 8 || keynum == 13
			|| keynum == 32) {
		return true;
	} else {
		return false;
	}
}
