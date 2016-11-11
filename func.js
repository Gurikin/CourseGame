// Очистка списка
function setClientTime() {
	var currentDate = new Date();
	var currentHour = currentDate.getHours();
	var currentMinutes = currentDate.getMinutes();
	var currentSeconds = currentDate.getSeconds();
	if (currentMinutes < 10) {
		currentMinutes = '0' + currentMinutes;
	}
	if (currentSeconds < 10) {
		currentSeconds = '0' + currentSeconds;
	}
	var timeString = currentHour + ':' + currentMinutes + ':' + currentSeconds;
	var clientTime = document.getElementById('ClientTime');
	clientTime.appendChild(document.createTextNode(timeString));
}
/*
** Функция возвращат объект XMLHttpRequest
*/
function getXmlHttpRequest()
{
	if (window.XMLHttpRequest) 
	{
		try 
		{
			return new XMLHttpRequest();
		} 
		catch (e){}
	} 
	else if (window.ActiveXObject) 
	{
		try 
		{
			return new ActiveXObject('Msxml2.XMLHTTP');
		} catch (e){}
		try 
		{
			return new ActiveXObject('Microsoft.XMLHTTP');
		} 
		catch (e){}
	}
	return null;
}
function clearList()
{
	var divResult = document.getElementById("divResult");
	while (divResult.hasChildNodes())
		divResult.removeChild(divResult.lastChild);
}

// Добавление нового элемента списка
function addListItem(text)
{
	if (text.length == 0) return;
	var divResult = document.getElementById("divResult");
	var p = document.createElement("p");
	divResult.appendChild(p);
	var pText = document.createTextNode(text);
	p.appendChild(pText);
}

// Запрос данных
function showBooks()
{
	var req = getXmlHttpRequest();
	req.onreadystatechange = function()
		{
			if (req.readyState != 4) return;
			var responseText = new String(req.responseText);
			var books = responseText.split('\n');
			clearList();
			for (var i = 0; i < books.length; i++)
				addListItem(books[i]);
		}
	var txtCat = document.getElementById("txtCat");
	req.open("GET", "localhost:8080?userName=" + txtCat.value, true);
	req.send(null);
}