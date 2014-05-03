

function details(filtre, lien)
{
	if ($(lien).text() == 'cacher') {
		$(filtre).hide('fast');
		$(lien).text('détails');
	} else {
		$(filtre).show('fast');
		$(lien).text('cacher');
	}
	return false;
}

function amuHide(filtre)
{
	$(filtre).hide('slow');
	return false;
}

function amuShow(filtre)
{
	$(filtre).show('slow');
	return false;
}

function amuHideShow(f1, f2)
{
	$(f1).hide('slow');
	$(f2).show('slow');
	return false;
}

// Copyright 2006,2007 Bontrager Connection, LLC
// http://bontragerconnection.com/ and http://www.willmaster.com/
// Version: July 28, 2007

var cX = 0; var cY = 0; var rX = 0; var rY = 0;

function UpdateCursorPosition(e){
	cX = e.pageX;
	cY = e.pageY;
	}

function UpdateCursorPositionDocAll(e)
	{
	cX = event.clientX;
	cY = event.clientY;
	}

if(document.all) {
	document.onmousemove = UpdateCursorPositionDocAll;
	}
else {
	document.onmousemove = UpdateCursorPosition;
	}


function AssignPosition(d) {
	if (self.pageYOffset) {
		rX = self.pageXOffset;
		rY = self.pageYOffset;
		}
	else if (document.documentElement && document.documentElement.scrollTop) {
		rX = document.documentElement.scrollLeft;
		rY = document.documentElement.scrollTop;
		}
	else if (document.body) {
		rX = document.body.scrollLeft;
		rY = document.body.scrollTop;
		}
	if(document.all) {
		cX += rX; 
		cY += rY;
		}
	d.style.left = (cX+10) + "px";
	d.style.top = (cY+10) + "px";
	}

function HideContent(d) {
if(d.length < 1) { return; }
document.getElementById(d).style.display = "none";
}

function ShowContent(d) {
if(d.length < 1) { return; }
var dd = document.getElementById(d);
AssignPosition(dd);
dd.style.display = "block";
}

function ReverseContentDisplay(d) {
if(d.length < 1) { return; }
var dd = document.getElementById(d);
AssignPosition(dd);
if(dd.style.display == "none") { dd.style.display = "block"; }
else { dd.style.display = "none"; }
}



/*
* Montre / Cache un div
*/
function MontrerCacher( divID, showClass )
	{
	if ( document.getElementById && document.getElementById( divID ) ) // Pour les navigateurs r�cents
		{
		Pdiv = document.getElementById( divID );
		PcH = true;
		}
	else if ( document.all && document.all[ divID ] ) // Pour les veilles versions
		{
		Pdiv = document.all[ divID ];
		PcH = true;
		}
	else if ( document.layers && document.layers[ divID ] ) // Pour les tr�s veilles versions
		{
		Pdiv = document.layers[ divID ];
		PcH = true;
		}
	else
		{

		PcH = false;
		}
	if ( PcH )
		{
		Pdiv.className = ( Pdiv.className == 'cachediv' ) ? showClass : 'cachediv';
		}
	}

function DivStatus( nom, numero )
	{
		var divID = nom + numero;
		if ( document.getElementById && document.getElementById( divID ) ) // Pour les navigateurs r�cents
			{
				Pdiv = document.getElementById( divID );
				PcH = true;
		 	}
		else if ( document.all && document.all[ divID ] ) // Pour les veilles versions
			{
				Pdiv = document.all[ divID ];
				PcH = true;
			}
		else if ( document.layers && document.layers[ divID ] ) // Pour les tr�s veilles versions
			{
				Pdiv = document.layers[ divID ];
				PcH = true;
			}
		else
			{

				PcH = false;
			}
		if ( PcH )
			{
				Pdiv.className = ( Pdiv.className == 'cachediv' ) ? '' : 'cachediv';
			}
	}

/*
* Cache tous les divs ayant le m�me pr�fixe
*/
function CacheTout( nom )
	{	
		var NumDiv = 1;
		if ( document.getElementById ) // Pour les navigateurs r�cents
			{
				while ( document.getElementById( nom + NumDiv) )
					{
						SetDiv = document.getElementById( nom + NumDiv );
						if ( SetDiv && SetDiv.className != 'cachediv' )
							{
								DivStatus( nom, NumDiv );
							}
						NumDiv++;
					}
			}
		else if ( document.all ) // Pour les veilles versions
			{
				while ( document.all[ nom + NumDiv ] )
					{
						SetDiv = document.all[ nom + NumDiv ];
						if ( SetDiv && SetDiv.className != 'cachediv' )
							{
								DivStatus( nom, NumDiv );
							}
						NumDiv++;
					}
			}
		else if ( document.layers ) // Pour les tr�s veilles versions
			{
				while ( document.layers[ nom + NumDiv ] )
					{
						SetDiv = document.layers[ nom + NumDiv ];
						if ( SetDiv && SetDiv.className != 'cachediv' )
							{
								DivStatus( nom, NumDiv );
							}
						NumDiv++;
					}
			}
	}

/*
* Montre tous les divs ayant le m�me pr�fixe
*/
function MontreTout( nom )
	{	
		var NumDiv = 1;
		if ( document.getElementById ) // Pour les navigateurs r�cents
			{
				while ( document.getElementById( nom + NumDiv) )
					{
						SetDiv = document.getElementById( nom + NumDiv );
						if ( SetDiv && SetDiv.className != '' )
							{
								DivStatus( nom, NumDiv );
							}
						NumDiv++;
					}
			}
		else if ( document.all ) // Pour les veilles versions
			{
				while ( document.all[ nom + NumDiv ] )
					{
						SetDiv = document.all[ nom + NumDiv ];
						if ( SetDiv && SetDiv.className != '' )
							{
								DivStatus( nom, NumDiv );
							}
						NumDiv++;
					}
			}
		else if ( document.layers ) // Pour les tr�s veilles versions
			{
				while ( document.layers[ nom + NumDiv ] )
					{
						SetDiv = document.layers[ nom + NumDiv ];
						if ( SetDiv && SetDiv.className != '' )
							{
								DivStatus( nom, NumDiv );
							}
						NumDiv++;
					}
			}
	}

/*
* Inverse les divs: Cache les divs visible et montre le divs cach�s :)
*/
function InverseTout( nom )
	{	
		var NumDiv = 1;
		if ( document.getElementById ) // Pour les navigateurs r�cents
			{
				while ( document.getElementById( nom + NumDiv ) )
					{
						SetDiv = document.getElementById( nom + NumDiv );
						DivStatus( nom, NumDiv );
						NumDiv++;
					}
			}
		else if ( document.all ) // Pour les veilles versions
			{
				while ( document.all[ nom + NumDiv ] )
					{
						SetDiv = document.all[ nom + NumDiv ];
						DivStatus( nom, NumDiv );
						NumDiv++;
					}
			}
		else if ( document.layers ) // Pour les tr�s veilles versions
			{
				while ( document.layers[ nom + NumDiv ] )
					{
						SetDiv = document.layers[ nom + NumDiv ];
						DivStatus( nom, NumDiv );
						NumDiv++;
					}
			}
	}
