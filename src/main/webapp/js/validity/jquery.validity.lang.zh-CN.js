$.extend($.validity.messages, {
  require:"#{field}必填",

  // Format validators:
  match:"#{field}无效格式",
  integer:"#{field}必须为整数",
  date:"#{field} muss als Datum formatiert werden. (mm/dd/yyyy)",
  email:"#{field} muss als Email formatiert werden.",
  usd:"#{field} muss als US Dollar Betrag formatiert werden.",
  url:"#{field} muss als URL formatiert werden.",
  number:"#{field}必需为数字",
  zip:"#{field} muss als Postleitzahl formatiert werden ##### oder #####-####.",
  phone:"#{field} muss als Telefonnummer formatiert werden ###-###-####.",
  guid:"#{field} muss als Guid Zufallszahl formatiert werden {3F2504E0-4F89-11D3-9A0C-0305E82C3301}.",
  time24:"#{field} muss als 24 Stunden Zeit formatiert werden: 23:00.",
  time12:"#{field} muss als 12 Stunden Zeit formatiert werden: 12:00 AM/PM",

  // Value range messages:
  lessThan:"#{field} muss weniger sein als #{max}.",
  lessThanOrEqualTo:"#{field} muss weniger sein als oder gleich an #{max}.",
  greaterThan:"#{field} muss größer sein dan #{min}.",
  greaterThanOrEqualTo:"#{field} muss größer sein dan oder gleich an #{min}.",
  range:"#{field} muss sein zwischen #{min} und #{max}.",

  // Value length messages:
  tooLong:"#{field}不能超过 #{max}个字符",
  tooShort:"#{field}不能短于 #{min}个字符",

  // Composition validators:
  nonHtml:"#{field} darf nicht HTML Zeichen enthalten.",
  alphabet:"#{field} enthalt nicht zulässige Zeichen.",

  minCharClass:"#{field} darf nicht mehr enthalten als #{min} #{charClass} Zeichen.",
  maxCharClass:"#{field} darf nicht weniger enthalten als #{min} #{charClass} Zeichen.",

  // Aggregate validator messages:
  equal:"值不匹配",
  distinct:"Ein Wert wurde wiederholt.",
  sum:" Werte können nicht hinzugefügt werden #{sum}.",
  sumMax:"Die Summe der Werte muss weniger sein als #{max}.",
  sumMin:"Die Summe der Werte muss mehr sein als #{min}.",

  // Radio validator messages:
  radioChecked:"Der ausgewählte Wert ist nicht gültig.",

  generic:"无效"
});

$.validity.setup({ defaultFieldName:"Dieses Feld", });
