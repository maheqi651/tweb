 
var width = 800;
var height =600;
var img_w = 50;
var img_h = 70;

function reflashsvg(){
	 img_w = 50;
	 img_h = 70;
	 width = 800;
     height =600;
	$("#map7").html("");
	var value1= $('#hbid').val();
	var value2= $('#zpid').val();
	width=width*value1;
	height=height*value1;
	img_w=4*value2;
	img_h=3*value2;
	 
	loadgrapshow();
}
function loadgrapshow(){
	  
	 
	var tzsfl=parmobject.tzsfl.split(",");
	//alert(tzsfl);
	var twbfl=parmobject.twbfl.split(",");
	var str="";
	for(var i=0;i<tzsfl.length;i++)
	{
		 
			str+=tzsfl[i]+",";
	}  
	
	
	for(var i=0;i<twbfl.length;i++)
	{
		 
			str+=twbfl[i]+",";
	} 
	
	var  svg = d3.select("#map7").append("svg")
							.attr("width",width)
							.attr("height",height);
	
	//alert(str);
	//TLGGraphQuery?idcard=522627199202080027"
	d3.json("TLGGraphQuery?deap="+$("#deapid").val()+"&idcard="+$("#idcard").val()+"&startdate="+$("#startdate").val()+"&enddate="+$("#enddate").val()+"&rrtype="+str,function(error,root)
			{
		root1=root;
		if( error ){
			return console.log(error);
		}
		console.log(root);
		
		var force = d3.layout.force()
						.nodes(root.nodes)
						.links(root.edges)
						.size([width,height])
						.linkDistance(200)
						.charge(-1500)
						.start();
		
		var edges_line = svg.selectAll("line")
							.data(root.edges)
							.enter()
							.append("line")
							.style("stroke","#ccc")
							.style("stroke-width",1);
							
		var edges_text = svg.selectAll(".linetext")
							.data(root.edges)
							.enter()
							.append("text")
							.attr("class","linetext")
							.text(function(d){
								return d.relation;
							})/*.style("fill-opacity",0.0)*/;
		
							
		var nodes_img = svg.selectAll("image")
							.data(root.nodes)
							.enter()
							.append("image")
							.attr("width",function(d){
								if(d.idcard==$("#idcard").val())
								{
									return img_w+20;
								}else{
									return img_w;
								}
							})
							.attr("height",function(d){
								if(d.idcard==$("#idcard").val())
							{
								return img_h+30;
							}else{
								return img_h;
							}}).style("cursor","pointer")
							.attr("xlink:href",function(d){
								return "ImgJpgQuery?idcard="+d.idcard;
								//"ImgQuery?idcard="+d.idcard;
							}).on("dblclick",function(d){
								loaddzda(d.idcard);
							})
						 	.on("mouseover",function(d,i){
						 		 
								//��ʾ�������ϵ�����
								edges_text.style("fill-opacity",function(edge){
									if( edge.source === d || edge.target === d ){
										return 1.0;
									} 
								});
							})
							.on("mouseout",function(d,i){
								//��ȥ�������ϵ�����
								edges_text.style("fill-opacity",function(edge){
									if( edge.source === d || edge.target === d )
									{
										return 0.0;
									} 
								});
							})  
							.call(force.drag);
		
		var text_dx = -20;
		var text_dy = 20;
		
		var nodes_text = svg.selectAll(".nodetext")
							.data(root.nodes)
							.enter()
							.append("text")
							.attr("class","nodetext")
							.attr("dx",function(d){
								if(d.idcard==$("#idcard").val())
								{
									return text_dx+10;
								}else{
									return text_dx;
								}
							})
							.attr("dy",function(d){
								if(d.idcard==$("#idcard").val())
								{
									return text_dy+30;
								}else{
									return text_dy;
								}
							})
							.text(function(d){
								return d.name;
							});
									
		force.on("tick", function(){
			//���ƽ��ı߽�
			root.nodes.forEach(function(d,i){
				d.x = d.x - img_w/2 < 0     ? img_w/2 : d.x ;
				d.x = d.x + img_w/2 > width ? width - img_w/2 : d.x ;
				d.y = d.y - img_h/2 < 0      ? img_h/2 : d.y ;
				d.y = d.y + img_h/2 + text_dy > height ? height - img_h/2 - text_dy : d.y ;
				
			});
		
			//���������ߵ�λ��
			 edges_line.attr("x1",function(d){ return d.source.x; });
			 edges_line.attr("y1",function(d){ return d.source.y; });
			 edges_line.attr("x2",function(d){ return d.target.x; });
			 edges_line.attr("y2",function(d){ return d.target.y; });
			 
			 //���������������ֵ�λ��
			 edges_text.attr("x",function(d){ return (d.source.x + d.target.x) / 2 ; });
			 edges_text.attr("y",function(d){ return (d.source.y + d.target.y) / 2 ; });
			 
			 
			 //���½��ͼƬ������
			 nodes_img.attr("x",function(d){ return d.x - img_w/2; });
			 nodes_img.attr("y",function(d){ return d.y - img_h/2; });
			 
			 nodes_text.attr("x",function(d){ return d.x });
			 nodes_text.attr("y",function(d){ return d.y + img_w/2; });
		});
	});
}