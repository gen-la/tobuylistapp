Felsökning:
Om man inte använder key i LazyColumn identifierar Compose varje rad utifrån dess position i 
listan (rad 0, rad 1, rad 2 och så vidare). Om en rad sparar något eget tillstånd med remember,
t.ex. en ikryssad ruta, kopplas det tillståndet till positionen och inte till själva varan.
Det ger inte något felmeddelande i Logcat eller i bygget. 
Exempel: listan innehåller Mjölk, Bröd och Ost.
Jag kryssar i Bröd (position 1) och sen raderar Mjölk. Listan blir då [Bröd, Ost] och alla
rader flyttas upp ett steg. Compose har fortfarande "ikryssad" sparat för position 1, och där 
ligger nu Ost. Då ser det ut som att Ost är ikryssad, fast det var Bröd jag kryssade i. 
Datan är rätt, men skärmen visar fel rad.
Lösningen är att använda key = { it.id } så att varje rad få ett unikt id.

LazyColumn(modifier = Modifier.weight(1f)) {
  items(viewModel.items, key = { it.id }) { item ->
    ShoppingItemRow(
      item = item,
      onToggle = { viewModel.toggleBought(item.id) },
      onEdit = { itemBeingEdited = item },
      onDelete = { viewModel.deleteItem(item.id) }
    )
  }
}


Hjälpmedel:
Jag använde kursmaterialet och AI. Kursmaterialet var bra för att komma igång med projektet
medan AI var bra på att hitta fel och komma med förslag på hur jag ska lösa de.
