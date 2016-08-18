function map(){
	var key = new Array();
	var value = new Array();
	
	this.getKeyIndex = function(_key){
		var len = key.length;
		if(len==0){
			return -1;
		}
		
		for ( var i = 0; i < len; i++) {
			if(key[i]==_key){
				return i;
			}
		}
		return -1;
	};
	
	this.put = function(_key){
		var index = this.getKeyIndex(_key);
		if(index==-1){
			key.push(_key);
			value.push(0);
		}else{
			value[index] += 1;
		}
	};
	
	this.get = function(_key){
		var index = this.getKeyIndex(_key);
		if(index==-1)
			return 0;
		else
			return value[index];
	};
	
	this.clear = function(){
		key.length = 0;
		value.length = 0;
	};
}
