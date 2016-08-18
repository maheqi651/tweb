;(function($){$(document).ready(
								function(){
									var initSwitchTab=function(){
									 
										var btns=$('div#front-page ul.nav-tabs li.item');
										var contents=$('div.service-inner div.inner-item');
										var className='active';
										btns.bind('click',
									 function(e){
									var target=$(this);var index=target.index();btns.removeClass(className);target.addClass(className);contents.hide().eq(index).show();e.stopPropagation();})}
initSwitchTab();var initNewWindow=function(){var target=$('div#service-box div.inner-wrap h2 a');target.bind('mouseenter',function(e){$(e.target).siblings('em.new-window').css('visibility','visible');}).bind('mouseleave',function(e){$(e.target).siblings('em.new-window').css('visibility','hidden');});}
initNewWindow();var initAppListShow=function(){var appBtn=$('div#app-list ul.other-apps li.app-icon');var appBox=$('div#app-list');appBtn.bind('mouseenter',function(e){var target=$(e.target).closest('li.app-icon');var index=target.index();target.siblings().stop().animate({opacity:".4"},600);target.stop().animate({opacity:"1"},600);appBox.find('div.content.active').hide().removeClass('active');var boxes=appBox.find('div.content');if(boxes.is(':animated')){boxes.stop();}
boxes.eq(index).fadeIn(600).addClass('active');});}
initAppListShow();var initBanner=function(){var box=$('#banner'),bgBox=box.find('.banner-bg'),imgBox=bgBox.find('.feature-image'),ul=box.find('.banner-ul'),lis=ul.find('.banner-li'),imgs=imgBox.find('img'),tid=null,k=0,l=lis.length,delay=200,autoDelay=4000;lis.each(function(i){var $this=$(this);$this.data('ind',i);if($(this).hasClass('banner-li-active')){k=i;return}});function changeImg(u){bgBox.animate({'opacity':0},{duration:delay,queue:false,complete:function(){this.style.background=imgs.eq(u).data('bg');imgs.hide();imgs.eq(u).show();lis.removeClass('banner-li-active').eq(u).addClass('banner-li-active');$(this).animate({'opacity':1},{duration:delay,queue:false,complete:function(){switch(true){case u+1<l:k=++u;break;case u+1>=l:k=0;break;}}});}});}
lis.on('click',function(){var $this=$(this);if(!$this.hasClass('banner-li-acitve')){changeImg($this.data('ind'));}});function autoPlay(){if(tid){clearInterval(tid)}
tid=setInterval(function(){changeImg(k)},autoDelay);}
function cancelAuto(){clearInterval(tid);}
box.on({'mouseenter':function(){cancelAuto();},'mouseleave':function(){autoPlay();}});changeImg(k);autoPlay();}
initBanner();});})(jQuery);