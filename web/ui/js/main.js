function getTop(){
	var _oSelfFunc = arguments.callee;
	if (!_oSelfFunc._moTop){
		try{
			_oSelfFunc._moTop=window!=parent?(parent.getTop?parent.getTop():parent.parent.getTop()):window;
		}catch(_oError){
			_oSelfFunc._moTop=window;
		}
	}
	return _oSelfFunc._moTop;
}
try{
	window.top=getTop();
}catch(e){
	eval("var top=getTop();");
}

function switchFolder(aN,aw){
	getTop().switchFolderComm(aN, aw||getLeftWin(), "folder", "li", "fn", "fs", getTop().switchFolder);
}

function getLeftWin(){
	return getTop();
}

function switchFolderComm(au,aj,Wb,oF,beJ,bdN,aMD){
	var alp=S(Wb,aj),gw=au;

	if(gw){
		aMD.biX=gw;
	}else{
		gw=aMD.biX;
	}

	if(alp){
		var aKC="SwiTchFoLdErComM_gLoBaldATa",aIt=aj[aKC],Ot;

		if(aIt!=gw){
			aAG(aIt,aj,bdN,"none");
		}

		if(Ot=aAG(aj[aKC]=gw,aj,beJ,"over")){
			E("new|personal|pop|tag".split("|"),function(aEK){
				var GQ=S(aEK+"folders",aj);
				//GQ&&isObjContainTarget(GQ,Ot)
				showFolders(aEK,true);
			});

			if(getStyle(alp,"overflow")!="hidden"){
				scrollIntoMidView(Ot,alp);
			}else{
				var GQ=S("ScrollFolder",aj);
				//GQ&&isObjContainTarget(GQ,Ot)&&
				scrollIntoMidView(Ot,GQ);
			}
		}
	}
}

function S(au,cP){
	try{
		return(cP&&(cP.document||cP)||document).getElementById(au);
	}catch(aH){
		return null;
	}
}

function aAG(cU,Cq,bdW,bO){
	if(cU){
		var aks=S(cU+"_td",Cq);
		if(aks){
			setClass(aks,bdW);
			return aks;
		}else{
			var akI=S(cU,Cq);
			if(akI){
				var aHk=bO=="over";
				if(aHk){
					showFolders(akI.name,true);
				}
				var aZn=S(cU,Cq).parentNode;
				setClass(aZn,aHk?"fn_list":"");
				return akI;
			}
		}
	}
}

function setClass(aS,sK){
	if(aS&&typeof(sK)!="undefined"&&aS.className!=sK){
		aS.className=sK;
	}
	return aS;
}

function E(uN,DY,apq,Al){
	if(!uN){
		return;
	}

	if(uN.length!=null){
		var aw=uN.length,je;

		if(Al<0){
			je=aw+Al;
		}else{
			je=Al<aw?Al:aw;
		}

		for(var i=(apq||0);i<je;i++){
			try{
				if(DY(uN[i],i,aw)===false){
					break;
				}
			}catch(aH){
				//ossLog("message="+aH.message+"\r\nline:"+aH.lineNumber+"\r\nfile:"+aH.fileName);
				//if(aH.message != null) ossLog("message="+aH.message);
			}
		}
	}else{
		for(var i in uN){
			try{
				if(DY(uN[i],i)===false){
					break;
				}
			}catch(aH){
				ossLog([aH.message,"<br>",DY],61882714);
			}
		}
	}
}

function ossLog(vMsg){
	try{
		if(window.debug != null && (typeof window.debug == "function")) {
			window.debug(vMsg);
		}
	}catch(ex){
		window.debug = function(){};
	}
}

function getStyle(aS,bet){
	var awm=aS&&(aS.currentStyle?aS.currentStyle:aS.ownerDocument.defaultView.getComputedStyle(aS,null));
	return awm&&awm[bet]||"";
}

function scrollIntoMidView(aS,dv,bqa,bnM,boX){
	if(!aS||!dv){
		return false;
	}

	var aGY=dv.tagName.toUpperCase()=="BODY",aD=dv.ownerDocument,IM=aD.documentElement;
	if(aGY&&IM.clientHeight){
		dv=IM;
	}

	var Hi=calcPos(aS)[0]-calcPos(dv)[0]-(aGY?dv.scrollTop:0),Is=Hi,Un=aS.offsetHeight,WY=dv.clientHeight,afa=bnM||0;

	if(bqa||Is<0||Is+Un>WY){
		var SK=0,zf;
		if(WY>Un+afa){
			if(boX){
				SK=Is<0?0:(WY-Un-afa);
			}else{
				SK=(WY-Un-afa)/2
			}
		}
		zf=dv.scrollTop=dv.scrollTop+Hi-SK;
		dv==IM&&(aD.body.scrollTop=zf);
	}
	return true;
}

function calcPos(aS,alL){
	var dg=0,gh=0,bJ=0,cj=0;

	if(aS&&aS.tagName){
		var cy=aS.parentNode,amD=cy&&cy.offsetParent,aMB=aS.offsetParent,cFj;

		gh+=aS.offsetLeft;
		dg+=aS.offsetTop;
		bJ=aS.offsetWidth;
		cj=aS.offsetHeight;

		while(amD){
			if(aMB==cy){
				gh+=cy.offsetLeft;
				dg+=cy.offsetTop;
				aMB=amD;
			}
			gh-=cy.scrollLeft;
			dg-=cy.scrollTop;
			cy=cy.parentNode;
			amD=cy.offsetParent;
		}
	}
	return alL=="json"?{top:dg,bottom:dg+cj,left:gh,right:gh+bJ,width:bJ,height:cj}:[dg,gh+bJ,dg+cj,gh,bJ,cj];
}