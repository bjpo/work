document.writeln("");
document.writeln("<link rel=stylesheet type=text\/css href=\"css\/dwtg.css\">");
document.writeln("<DIV id=xixi onmouseover=toBig() onmouseout=toSmall()>");
document.writeln("<TABLE style=\"FLOAT: left;width:157px;height:300px; border:1px solid;\" cellSpacing=0 cellPadding=0>");
document.writeln("  <TBODY>");
document.writeln("  <TR><\/TR>");
document.writeln("  <TR>");
document.writeln("    <TD class=info vAlign=top><p>全校出勤情况<\/p>");
document.writeln("      <p>正常出席：89%<\/p>");
document.writeln("      <p>缺席：3%<\/p>");
document.writeln("      <p>迟到：3%<\/p>");
document.writeln("      <p>早退：无<\/p><\/TD><\/TR>");
document.writeln("  <TR>");
document.writeln("    <TD class=down_kefu vAlign=top><\/TD><\/TR><\/TBODY><\/TABLE>");
document.writeln("<DIV class=Obtn><\/DIV><\/DIV>");
document.writeln("<SCRIPT language=javascript>");
document.writeln("		考勤系统=function (id,_top,_left){");
document.writeln("		var me=id.charAt?document.getElementById(id):id, d1=document.body, d2=document.documentElement;");
document.writeln("		d1.style.height=d2.style.height=\'100%\';me.style.top=_top?_top+\'px\':0;me.style.left=_left+\"px\";\/\/[(_left>0?\'left\':\'left\')]=_left?Math.abs(_left)+\'px\':0;");
document.writeln("		me.style.position=\'absolute\';");
document.writeln("		setInterval(function (){me.style.top=parseInt(me.style.top)+(Math.max(d1.scrollTop,d2.scrollTop)+_top-parseInt(me.style.top))*0.1+\'px\';},10+parseInt(Math.random()*20));");
document.writeln("		return arguments.callee;");
document.writeln("		};");
document.writeln("		window.onload=function (){");
document.writeln("		考勤系统");
document.writeln("		(\'xixi\',100,-152)");
document.writeln("		}");
document.writeln("	<\/SCRIPT>");
document.writeln("");
document.writeln("<SCRIPT language=javascript> ");
document.writeln("			lastScrollY=0; ");
document.writeln("			");
document.writeln("			var InterTime = 1;");
document.writeln("			var maxWidth=-1;");
document.writeln("			var minWidth=-152;");
document.writeln("			var numInter = 8;");
document.writeln("			");
document.writeln("			var BigInter ;");
document.writeln("			var SmallInter ;");
document.writeln("			");
document.writeln("			var o =  document.getElementById(\"xixi\");");
document.writeln("				var i = parseInt(o.style.left);");
document.writeln("				function Big()");
document.writeln("				{");
document.writeln("					if(parseInt(o.style.left)<maxWidth)");
document.writeln("					{");
document.writeln("						i = parseInt(o.style.left);");
document.writeln("						i += numInter;	");
document.writeln("						o.style.left=i+\"px\";	");
document.writeln("						if(i==maxWidth)");
document.writeln("							clearInterval(BigInter);");
document.writeln("					}");
document.writeln("				}");
document.writeln("				function toBig()");
document.writeln("				{");
document.writeln("					clearInterval(SmallInter);");
document.writeln("					clearInterval(BigInter);");
document.writeln("						BigInter = setInterval(Big,InterTime);");
document.writeln("				}");
document.writeln("				function Small()");
document.writeln("				{");
document.writeln("					if(parseInt(o.style.left)>minWidth)");
document.writeln("					{");
document.writeln("						i = parseInt(o.style.left);");
document.writeln("						i -= numInter;");
document.writeln("						o.style.left=i+\"px\";");
document.writeln("						");
document.writeln("						if(i==minWidth)");
document.writeln("							clearInterval(SmallInter);");
document.writeln("					}");
document.writeln("				}");
document.writeln("				function toSmall()");
document.writeln("				{");
document.writeln("					clearInterval(SmallInter);");
document.writeln("					clearInterval(BigInter);");
document.writeln("					SmallInter = setInterval(Small,InterTime);");
document.writeln("					");
document.writeln("				}");
document.writeln("				");
document.writeln("<\/SCRIPT>");
document.writeln("")