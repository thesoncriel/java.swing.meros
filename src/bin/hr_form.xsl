<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/horseRace">
<html>
	<head>
		<title>
		<xsl:value-of select="header/author"/>
		</title>
		<style type="text/css">
			body{background-color:#eeeeee;}

			.title{
				font-size:26px;
				font-weight:bold;
				font-family:맑은 고딕, 굴림;
				letter-spacing:1.2em;
			}

			.leftPane{
				width:200px;
				padding-top:2em;
				text-align:center;
				float:left;
			}
			.image{
				width:150px;
				height:200px;
				border-color:#41aae0;
				border-width:2px;
				border-style:solid;
			}

			.rightPane{
				width:310px;
				padding-top:30px;
				text-align:left;
				float:left;
				line-height:1.7em;
			}
			.fieldName{
				width:110px;
				font-style:italic;
				font-weight:bold;
				font-family:맑은 고딕, 굴림;
				font-size:15px;
				text-align:right;
			}
			.fieldValue{
				width:190px;
				font-style:italic;
				font-family:맑은 고딕, 굴림;
				font-size:15px;
				text-align:left;
			}
			.graph{
				width:250px;
				height:200px
				border-color:#41aa41;
				border-width:2px;
				border-style:solid;
			}

		</style>
	</head>

	<body>
		<div class="title">:::: 문서 보기 ::::</div>
		<div class="leftPane">
			<img class="image">
				<xsl:attribute name="src">
					<xsl:value-of select="body/players/player/picURL"/>
				</xsl:attribute>
			</img>
		</div>
		<div class="rightPane">
			<span class="fieldName">선수 식별 번호 :</span>
			<span class="fieldValue"><xsl:value-of select="body/players/player/@id"/></span><br/>
			<span class="fieldName">이름 :</span>
			<span class="fieldValue"><xsl:value-of select="body/players/player/name"/></span><br/>
			<span class="fieldName">나이 :</span>
			<span class="fieldValue"><xsl:value-of select="body/players/player/age"/></span><br/>
			<span class="fieldName">성별 :</span>
			<span class="fieldValue"><xsl:value-of select="body/players/player/gender"/></span><br/>
			<span class="fieldName">신장(m) :</span>
			<span class="fieldValue"><xsl:value-of select="body/players/player/height"/></span><br/>
			<span class="fieldName">몸무게(kg) :</span>
			<span class="fieldValue"><xsl:value-of select="body/players/player/weight"/></span><br/>
			<span class="fieldName">경주 파트너 :</span>
			<span class="fieldValue"><xsl:value-of select="body/players/player/hId"/></span><br/>
			<br/>
			<img src="pic/chart0.png" class="graph"></img>
			<br/>
			<br/>
			<img src="pic/chart1.png" class="graph"></img>
		</div>

	</body>

</html>
</xsl:template>
</xsl:stylesheet>