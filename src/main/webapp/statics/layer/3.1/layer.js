! function(i, d) {
	var l = {
		btnOk: "确定",
		btnCancel: "取消",
		title: "信息",
		promptTipA: "最多输入",
		promptTipB: "个字符",
		noPicture: "没有图片",
		photoError: "当前图片地址异常<br>是否继续查看下一张？",
		photoNextPage: "下一张",
		photoClose: "不看了"
	};
	if (i.lang == "en") {
		l = {
			btnOk: "Ok",
			btnCancel: "Cancle",
			title: "Information",
			promptTipA: "Enter ",
			promptTipB: "character at most.",
			noPicture: "No picture",
			photoError: "Current image address error.<br>Next slide?",
			photoNextPage: "The next",
			photoClose: "Close"
		}
	}
	var e = i.layui && layui.define,
		f, h, j = {
			getPath: function() {
				var o = document.scripts,
					m = o[o.length - 1],
					n = m.src;
				if (m.getAttribute("merge")) {
					return
				}
				return n.substring(0, n.lastIndexOf("/") + 1)
			}(),
			config: {},
			end: {},
			minIndex: 0,
			minLeft: [],
			btn: [l.btnOk, l.btnCancel],
			type: ["dialog", "page", "iframe", "loading", "tips"]
		};
	var g = {
		v: "3.0.3",
		ie: function() {
			var m = navigator.userAgent.toLowerCase();
			return (!!i.ActiveXObject || "ActiveXObject" in i) ? ((m.match(/msie\s(\d+)/) || [])[1] || "11") : false
		}(),
		index: (i.layer && i.layer.v) ? 100000 : 0,
		path: j.getPath,
		config: function(m, n) {
			m = m || {};
			g.cache = j.config = f.extend({}, j.config, m);
			g.path = j.config.path || g.path;
			typeof m.extend === "string" && (m.extend = [m.extend]);
			if (j.config.path) {
				g.ready()
			}
			if (!m.extend) {
				return this
			}
			e ? layui.addcss("modules/layer/" + m.extend) : g.link("skin/" + m.extend);
			return this
		},
		link: function(m, t, n) {
			if (!g.path) {
				return
			}
			var u = f("head")[0],
				s = document.createElement("link");
			if (typeof t === "string") {
				n = t
			}
			var p = (n || m).replace(/\.|\//g, "");
			var o = "layuicss-" + p,
				r = 0;
			s.rel = "stylesheet";
			s.href = g.path + m;
			s.id = o;
			if (!f("#" + o)[0]) {
				u.appendChild(s)
			}
			if (typeof t !== "function") {
				return
			}(function q() {
				if (++r > 8 * 1000 / 100) {
					return i.console && console.error("layer.css: Invalid")
				}
				parseInt(f("#" + o).css("width")) === 1989 ? t() : setTimeout(q, 100)
			}())
		},
		ready: function(o) {
			var n = "skinlayercss",
				m = "303";
			e ? layui.addcss("modules/layer/default/layer.css?v=" + g.v + m, o, n) : g.link("skin/default/layer.css?v=" + g.v + m, o, n);
			return this
		},
		alert: function(o, m, p) {
			var n = typeof m === "function";
			if (n) {
				p = m
			}
			return g.open(f.extend({
				content: o,
				yes: p
			}, n ? {} : m))
		},
		confirm: function(p, m, q, o) {
			var n = typeof m === "function";
			if (n) {
				o = q;
				q = m
			}
			return g.open(f.extend({
				content: p,
				btn: j.btn,
				yes: q,
				btn2: o
			}, n ? {} : m))
		},
		msg: function(p, n, m) {
			var o = typeof n === "function",
				s = j.config.skin;
			var r = (s ? s + " " + s + "-msg" : "") || "layui-layer-msg";
			var q = c.anim.length - 1;
			if (o) {
				m = n
			}
			return g.open(f.extend({
				content: p,
				time: 3000,
				shade: false,
				skin: r,
				title: false,
				closeBtn: false,
				btn: false,
				resize: false,
				end: m
			}, (o && !j.config.skin) ? {
				skin: r + " layui-layer-hui",
				anim: q
			} : function() {
				n = n || {};
				if (n.icon === -1 || n.icon === d && !j.config.skin) {
					n.skin = r + " " + (n.skin || "layui-layer-hui")
				}
				return n
			}()))
		},
		load: function(n, m) {
			return g.open(f.extend({
				type: 3,
				icon: n || 0,
				resize: false,
				shade: 0.01
			}, m))
		},
		tips: function(o, m, n) {
			return g.open(f.extend({
				type: 4,
				content: [o, m],
				closeBtn: false,
				time: 3000,
				shade: false,
				resize: false,
				fixed: false,
				maxWidth: 210
			}, n))
		}
	};
	var b = function(m) {
		var n = this;
		n.index = ++g.index;
		n.config = f.extend({}, n.config, j.config, m);
		document.body ? n.creat() : setTimeout(function() {
			n.creat()
		}, 30)
	};
	b.pt = b.prototype;
	var c = ["layui-layer", ".layui-layer-title", ".layui-layer-main", ".layui-layer-dialog", "layui-layer-iframe", "layui-layer-content", "layui-layer-btn", "layui-layer-close"];
	c.anim = ["layer-anim", "layer-anim-01", "layer-anim-02", "layer-anim-03", "layer-anim-04", "layer-anim-05", "layer-anim-06"];
	b.pt.config = {
		type: 0,
		shade: 0.3,
		fixed: true,
		move: c[1],
		title: l.title,
		offset: "auto",
		area: "auto",
		closeBtn: 1,
		time: 0,
		zIndex: 19891014,
		maxWidth: 360,
		anim: 0,
		isOutAnim: true,
		icon: -1,
		moveType: 1,
		resize: true,
		scrollbar: true,
		tips: 2
	};
	b.pt.vessel = function(q, u) {
		var r = this,
			m = r.index,
			p = r.config;
		var t = p.zIndex + m,
			o = typeof p.title === "object";
		var s = p.maxmin && (p.type === 1 || p.type === 2);
		var n = (p.title ? '<div class="layui-layer-title" style="' + (o ? p.title[1] : "") + '">' + (o ? p.title[0] : p.title) + "</div>" : "");
		p.zIndex = t;
		u([p.shade ? ('<div class="layui-layer-shade" id="layui-layer-shade' + m + '" times="' + m + '" style="' + ("z-index:" + (t - 1) + "; background-color:" + (p.shade[1] || "#000") + "; opacity:" + (p.shade[0] || p.shade) + "; filter:alpha(opacity=" + (p.shade[0] * 100 || p.shade * 100) + ");") + '"></div>') : "", '<div class="' + c[0] + (" layui-layer-" + j.type[p.type]) + (((p.type == 0 || p.type == 2) && !p.shade) ? " layui-layer-border" : "") + " " + (p.skin || "") + '" id="' + c[0] + m + '" type="' + j.type[p.type] + '" times="' + m + '" showtime="' + p.time + '" conType="' + (q ? "object" : "string") + '" style="z-index: ' + t + "; width:" + p.area[0] + ";height:" + p.area[1] + (p.fixed ? "" : ";position:absolute;") + '">' + (q && p.type != 2 ? "" : n) + '<div id="' + (p.id || "") + '" class="layui-layer-content' + ((p.type == 0 && p.icon !== -1) ? " layui-layer-padding" : "") + (p.type == 3 ? " layui-layer-loading" + p.icon : "") + '">' + (p.type == 0 && p.icon !== -1 ? '<i class="layui-layer-ico layui-layer-ico' + p.icon + '"></i>' : "") + (p.type == 1 && q ? "" : (p.content || "")) + '</div><span class="layui-layer-setwin">' + function() {
			var v = s ? '<a class="layui-layer-min" href="javascript:;"><cite></cite></a><a class="layui-layer-ico layui-layer-max" href="javascript:;"></a>' : "";
			p.closeBtn && (v += '<a class="layui-layer-ico ' + c[7] + " " + c[7] + (p.title ? p.closeBtn : (p.type == 4 ? "1" : "2")) + '" href="javascript:;"></a>');
			return v
		}() + "</span>" + (p.btn ? function() {
			var x = "";
			typeof p.btn === "string" && (p.btn = [p.btn]);
			for (var w = 0, v = p.btn.length; w < v; w++) {
				x += '<a class="' + c[6] + "" + w + '">' + p.btn[w] + "</a>"
			}
			return '<div class="' + c[6] + (p.btnAlign ? (" layui-layer-btn-" + p.btnAlign) : "") + '">' + x + "</div>"
		}() : "") + (p.resize ? '<span class="layui-layer-resize"></span>' : "") + "</div>"], n, f('<div class="layui-layer-move"></div>'));
		return r
	};
	b.pt.creat = function() {
		var t = this,
			o = t.config,
			m = t.index,
			r, u = o.content,
			q = typeof u === "object",
			s = f("body");
		if (o.id && f("#" + o.id)[0]) {
			return
		}
		if (typeof o.area === "string") {
			o.area = o.area === "auto" ? ["", ""] : [o.area, ""]
		}
		if (o.shift) {
			o.anim = o.shift
		}
		if (g.ie == 6) {
			o.fixed = false
		}
		switch (o.type) {
			case 0:
				o.btn = ("btn" in o) ? o.btn : j.btn[0];
				g.closeAll("dialog");
				break;
			case 2:
				var u = o.content = q ? o.content : [o.content, "auto"];
				o.content = '<iframe scrolling="' + (o.content[1] || "auto") + '" allowtransparency="true" id="' + c[4] + "" + m + '" name="' + c[4] + "" + m + '" onload="this.className=\'\';" class="layui-layer-load" frameborder="0"></iframe><form id="' + c[4] + "-form" + m + '" action="' + o.content[0] + '" method="post" target="' + c[4] + "" + m + '">';
				break;
			case 3:
				delete o.title;
				delete o.closeBtn;
				o.icon === -1 && (o.icon === 0);
				g.closeAll("loading");
				break;
			case 4:
				q || (o.content = [o.content, "body"]);
				o.follow = o.content[1];
				o.content = o.content[0] + '<i class="layui-layer-TipsG"></i>';
				delete o.title;
				o.tips = typeof o.tips === "object" ? o.tips : [o.tips, true];
				o.tipsMore || g.closeAll("tips");
				break
		}
		t.vessel(q, function(x, w, y) {
			s.append(x[0]);
			q ? function() {
				(o.type == 2 || o.type == 4) ? function() {
					f("body").append(x[1])
				}() : function() {
					if (!u.parents("." + c[0])[0]) {
						u.data("display", u.css("display")).show().addClass("layui-layer-wrap").wrap(x[1]);
						f("#" + c[0] + m).find("." + c[5]).before(w)
					}
				}()
			}() : s.append(x[1]);
			f(".layui-layer-move")[0] || s.append(j.moveElem = y);
			t.layero = f("#" + c[0] + m);
			o.scrollbar || c.html.css("overflow", "hidden").attr("layer-full", m)
		}).auto(m);
		if (o.type == 2) {
			var n = t.layero.find("#" + c[4] + "-form" + m),
				p = o.contentFormData;
			for (var v in p || {}) {
				n.append('<input type="hidden" name="' + v + '" value="' + p[v] + '"/>')
			}
			n.submit()
		}
		o.type == 4 ? t.tips() : t.offset();
		if (o.fixed) {
			h.on("resize", function() {
				t.offset();
				(/^\d+%$/.test(o.area[0]) || /^\d+%$/.test(o.area[1])) && t.auto(m);
				o.type == 4 && t.tips()
			})
		}
		o.time <= 0 || setTimeout(function() {
			g.close(t.index)
		}, o.time);
		t.move().callback();
		if (c.anim[o.anim]) {
			t.layero.addClass(c.anim[o.anim])
		}
		if (o.isOutAnim) {
			t.layero.data("isOutAnim", true)
		}
	};
	b.pt.auto = function(q) {
		var s = this,
			p = s.config,
			o = f("#" + c[0] + q);
		if (p.area[0] === "" && p.maxWidth > 0) {
			if (g.ie && g.ie < 8 && p.btn) {
				o.width(o.innerWidth())
			}
			o.outerWidth() > p.maxWidth && o.width(p.maxWidth)
		}
		var r = [o.innerWidth(), o.innerHeight()];
		var t = o.find(c[1]).outerHeight() || 0;
		var n = o.find("." + c[6]).outerHeight() || 0;

		function m(u) {
			u = o.find(u);
			u.height(r[1] - t - n - 2 * (parseFloat(u.css("padding-top")) | 0))
		}
		switch (p.type) {
			case 2:
				m("iframe");
				break;
			default:
				if (p.area[1] === "") {
					if (p.fixed && r[1] >= h.height()) {
						r[1] = h.height();
						m("." + c[5])
					}
				} else {
					m("." + c[5])
				}
				break
		}
		return s
	};
	b.pt.offset = function() {
		var q = this,
			n = q.config,
			m = q.layero;
		var p = [m.outerWidth(), m.outerHeight()];
		var o = typeof n.offset === "object";
		q.offsetTop = (h.height() - p[1]) / 2;
		q.offsetLeft = (h.width() - p[0]) / 2;
		if (o) {
			q.offsetTop = n.offset[0];
			q.offsetLeft = n.offset[1] || q.offsetLeft
		} else {
			if (n.offset !== "auto") {
				if (n.offset === "t") {
					q.offsetTop = 0
				} else {
					if (n.offset === "r") {
						q.offsetLeft = h.width() - p[0]
					} else {
						if (n.offset === "b") {
							q.offsetTop = h.height() - p[1]
						} else {
							if (n.offset === "l") {
								q.offsetLeft = 0
							} else {
								if (n.offset === "lt") {
									q.offsetTop = 0;
									q.offsetLeft = 0
								} else {
									if (n.offset === "lb") {
										q.offsetTop = h.height() - p[1];
										q.offsetLeft = 0
									} else {
										if (n.offset === "rt") {
											q.offsetTop = 0;
											q.offsetLeft = h.width() - p[0]
										} else {
											if (n.offset === "rb") {
												q.offsetTop = h.height() - p[1];
												q.offsetLeft = h.width() - p[0]
											} else {
												q.offsetTop = n.offset
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (!n.fixed) {
			q.offsetTop = /%$/.test(q.offsetTop) ? h.height() * parseFloat(q.offsetTop) / 100 : parseFloat(q.offsetTop);
			q.offsetLeft = /%$/.test(q.offsetLeft) ? h.width() * parseFloat(q.offsetLeft) / 100 : parseFloat(q.offsetLeft);
			q.offsetTop += h.scrollTop();
			q.offsetLeft += h.scrollLeft()
		}
		if (m.attr("minLeft")) {
			q.offsetTop = h.height() - (m.find(c[1]).outerHeight() || 0);
			q.offsetLeft = m.css("left")
		}
		q.offsetTop = q.offsetTop > 0 ? q.offsetTop : 0;
		m.css({
			top: q.offsetTop,
			left: q.offsetLeft
		})
	};
	b.pt.tips = function() {
		var t = this,
			s = t.config,
			o = t.layero;
		var r = [o.outerWidth(), o.outerHeight()],
			n = f(s.follow);
		if (!n[0]) {
			n = f("body")
		}
		var q = {
				width: n.outerWidth(),
				height: n.outerHeight(),
				top: n.offset().top,
				left: n.offset().left
			},
			p = o.find(".layui-layer-TipsG");
		var m = s.tips[0];
		s.tips[1] || p.remove();
		q.autoLeft = function() {
			if (q.left + r[0] - h.width() > 0) {
				q.tipLeft = q.left + q.width - r[0];
				p.css({
					right: 12,
					left: "auto"
				})
			} else {
				q.tipLeft = q.left
			}
		};
		q.where = [function() {
			q.autoLeft();
			q.tipTop = q.top - r[1] - 10;
			p.removeClass("layui-layer-TipsB").addClass("layui-layer-TipsT").css("border-right-color", s.tips[1])
		}, function() {
			q.tipLeft = q.left + q.width + 10;
			q.tipTop = q.top;
			p.removeClass("layui-layer-TipsL").addClass("layui-layer-TipsR").css("border-bottom-color", s.tips[1])
		}, function() {
			q.autoLeft();
			q.tipTop = q.top + q.height + 10;
			p.removeClass("layui-layer-TipsT").addClass("layui-layer-TipsB").css("border-right-color", s.tips[1])
		}, function() {
			q.tipLeft = q.left - r[0] - 10;
			q.tipTop = q.top;
			p.removeClass("layui-layer-TipsR").addClass("layui-layer-TipsL").css("border-bottom-color", s.tips[1])
		}];
		q.where[m - 1]();
		if (m === 1) {
			q.top - (h.scrollTop() + r[1] + 8 * 2) < 0 && q.where[2]()
		} else {
			if (m === 2) {
				h.width() - (q.left + q.width + r[0] + 8 * 2) > 0 || q.where[3]()
			} else {
				if (m === 3) {
					(q.top - h.scrollTop() + q.height + r[1] + 8 * 2) - h.height() > 0 && q.where[0]()
				} else {
					if (m === 4) {
						r[0] + 8 * 2 - q.left > 0 && q.where[1]()
					}
				}
			}
		}
		o.find("." + c[5]).css({
			"background-color": s.tips[1],
			"padding-right": (s.closeBtn ? "30px" : "")
		});
		o.css({
			left: q.tipLeft - (s.fixed ? h.scrollLeft() : 0),
			top: q.tipTop - (s.fixed ? h.scrollTop() : 0)
		})
	};
	b.pt.move = function() {
		var q = this,
			n = q.config,
			p = f(document),
			m = q.layero,
			o = m.find(n.move),
			r = m.find(".layui-layer-resize"),
			s = {};
		if (n.move) {
			o.css("cursor", "move")
		}
		o.on("mousedown", function(t) {
			t.preventDefault();
			if (n.move) {
				s.moveStart = true;
				s.offset = [t.clientX - parseFloat(m.css("left")), t.clientY - parseFloat(m.css("top"))];
				j.moveElem.css("cursor", "move").show()
			}
		});
		r.on("mousedown", function(t) {
			t.preventDefault();
			s.resizeStart = true;
			s.offset = [t.clientX, t.clientY];
			s.area = [m.outerWidth(), m.outerHeight()];
			j.moveElem.css("cursor", "se-resize").show()
		});
		p.on("mousemove", function(w) {
			if (s.moveStart) {
				var y = w.clientX - s.offset[0],
					x = w.clientY - s.offset[1],
					u = m.css("position") === "fixed";
				w.preventDefault();
				s.stX = u ? 0 : h.scrollLeft();
				s.stY = u ? 0 : h.scrollTop();
				if (!n.moveOut) {
					var t = h.width() - m.outerWidth() + s.stX,
						v = h.height() - m.outerHeight() + s.stY;
					y < s.stX && (y = s.stX);
					y > t && (y = t);
					x < s.stY && (x = s.stY);
					x > v && (x = v)
				}
				m.css({
					left: y,
					top: x
				})
			}
			if (n.resize && s.resizeStart) {
				var y = w.clientX - s.offset[0],
					x = w.clientY - s.offset[1];
				w.preventDefault();
				g.style(q.index, {
					width: s.area[0] + y,
					height: s.area[1] + x
				});
				s.isResize = true;
				n.resizing && n.resizing(m)
			}
		}).on("mouseup", function(t) {
			if (s.moveStart) {
				delete s.moveStart;
				j.moveElem.hide();
				n.moveEnd && n.moveEnd(m)
			}
			if (s.resizeStart) {
				delete s.resizeStart;
				j.moveElem.hide()
			}
		});
		return q
	};
	b.pt.callback = function() {
		var p = this,
			m = p.layero,
			n = p.config;
		p.openLayer();
		if (n.success) {
			if (n.type == 2) {
				m.find("iframe").on("load", function() {
					n.success(m, p.index)
				})
			} else {
				n.success(m, p.index)
			}
		}
		g.ie == 6 && p.IE6(m);
		m.find("." + c[6]).children("a").on("click", function() {
			var q = f(this).index();
			if (q === 0) {
				if (n.yes) {
					var r = n.yes(p.index, m);
					r === false || g.close(p.index)
				} else {
					if (n.btn1) {
						var r = n.btn1(p.index, m);
						r === false || g.close(p.index)
					} else {
						g.close(p.index)
					}
				}
			} else {
				var r = n["btn" + (q + 1)] && n["btn" + (q + 1)](p.index, m);
				r === false || g.close(p.index)
			}
		});

		function o() {
			var q = n.cancel && n.cancel(p.index, m);
			q === false || g.close(p.index)
		}
		m.find("." + c[7]).on("click", o);
		if (n.shadeClose) {
			f("#layui-layer-shade" + p.index).on("click", function() {
				g.close(p.index)
			})
		}
		m.find(".layui-layer-min").on("click", function() {
			var q = n.min && n.min(m);
			q === false || g.min(p.index, n)
		});
		m.find(".layui-layer-max").on("click", function() {
			if (f(this).hasClass("layui-layer-maxmin")) {
				g.restore(p.index);
				n.restore && n.restore(m)
			} else {
				g.full(p.index, n);
				setTimeout(function() {
					n.full && n.full(m)
				}, 100)
			}
		});
		n.end && (j.end[p.index] = n.end)
	};
	j.reselect = function() {
		f.each(f("select"), function(m, n) {
			var o = f(this);
			if (!o.parents("." + c[0])[0]) {
				(o.attr("layer") == 1 && f("." + c[0]).length < 1) && o.removeAttr("layer").show()
			}
			o = null
		})
	};
	b.pt.IE6 = function(m) {
		f("select").each(function(n, o) {
			var p = f(this);
			if (!p.parents("." + c[0])[0]) {
				p.css("display") === "none" || p.attr({
					layer: "1"
				}).hide()
			}
			p = null
		})
	};
	b.pt.openLayer = function() {
		var m = this;
		g.zIndex = m.config.zIndex;
		g.setTop = function(n) {
			var o = function() {
				g.zIndex++;
				n.css("z-index", g.zIndex + 1)
			};
			g.zIndex = parseInt(n[0].style.zIndex);
			n.on("mousedown", o);
			return g.zIndex
		}
	};
	j.record = function(m) {
		var n = [m.width(), m.height(), m.position().top, m.position().left + parseFloat(m.css("margin-left"))];
		m.find(".layui-layer-max").addClass("layui-layer-maxmin");
		m.attr({
			area: n
		})
	};
	j.rescollbar = function(m) {
		if (c.html.attr("layer-full") == m) {
			if (c.html[0].style.removeProperty) {
				c.html[0].style.removeProperty("overflow")
			} else {
				c.html[0].style.removeAttribute("overflow")
			}
			c.html.removeAttr("layer-full")
		}
	};
	i.layer = g;
	g.$ = g.jQuery = jQuery;
	g.window = i;
	g.getChildFrame = function(m, n) {
		n = n || f("." + c[4]).attr("times");
		return f("#" + c[0] + n).find("iframe").contents().find(m)
	};
	g.getFrameIndex = function(m) {
		return f("#" + m).parents("." + c[4]).attr("times")
	};
	g.iframeAuto = function(p) {
		if (!p) {
			return
		}
		var o = g.getChildFrame("html", p).outerHeight();
		var n = f("#" + c[0] + p);
		var q = n.find(c[1]).outerHeight() || 0;
		var m = n.find("." + c[6]).outerHeight() || 0;
		n.css({
			height: o + q + m
		});
		n.find("iframe").css({
			height: o
		})
	};
	g.iframeSrc = function(n, m) {
		f("#" + c[0] + n).find("iframe").attr("src", m)
	};
	g.iframeWindow = function(m) {
		var n = f("#" + c[0] + m).find("iframe");
		if (n.length > 0) {
			return n[0].contentWindow
		}
		return null
	};
	g.style = function(q, u, n) {
		var r = f("#" + c[0] + q),
			s = r.find(".layui-layer-content"),
			t = r.attr("type"),
			m = r.find(c[1]).outerHeight() || 0,
			p = r.find("." + c[6]).outerHeight() || 0,
			o = r.attr("minLeft");
		if (t === j.type[3] || t === j.type[4]) {
			return
		}
		if (!n) {
			if (parseFloat(u.width) <= 260) {
				u.width = 260
			}
			if (parseFloat(u.height) - m - p <= 64) {
				u.height = 64 + m + p
			}
		}
		r.css(u);
		p = r.find("." + c[6]).outerHeight();
		if (t === j.type[2]) {
			r.find("iframe").css({
				height: parseFloat(u.height) - m - p
			})
		} else {
			s.css({
				height: parseFloat(u.height) - m - p - parseFloat(s.css("padding-top")) - parseFloat(s.css("padding-bottom"))
			})
		}
	};
	g.min = function(p, o) {
		var n = f("#" + c[0] + p),
			r = n.find(c[1]).outerHeight() || 0,
			q = n.attr("minLeft") || (181 * j.minIndex) + "px",
			m = n.css("position");
		j.record(n);
		if (j.minLeft[0]) {
			q = j.minLeft[0];
			j.minLeft.shift()
		}
		n.attr("position", m);
		g.style(p, {
			width: 180,
			height: r,
			left: q,
			top: h.height() - r,
			position: "fixed",
			overflow: "hidden"
		}, true);
		n.find(".layui-layer-min").hide();
		n.attr("type") === "page" && n.find(c[4]).hide();
		j.rescollbar(p);
		if (!n.attr("minLeft")) {
			j.minIndex++
		}
		n.attr("minLeft", q)
	};
	g.restore = function(n) {
		var m = f("#" + c[0] + n),
			p = m.attr("area").split(",");
		var o = m.attr("type");
		g.style(n, {
			width: parseFloat(p[0]),
			height: parseFloat(p[1]),
			top: parseFloat(p[2]),
			left: parseFloat(p[3]),
			position: m.attr("position"),
			overflow: "visible"
		}, true);
		m.find(".layui-layer-max").removeClass("layui-layer-maxmin");
		m.find(".layui-layer-min").show();
		m.attr("type") === "page" && m.find(c[4]).show();
		j.rescollbar(n)
	};
	g.full = function(n) {
		var m = f("#" + c[0] + n),
			o;
		j.record(m);
		if (!c.html.attr("layer-full")) {
			c.html.css("overflow", "hidden").attr("layer-full", n)
		}
		clearTimeout(o);
		o = setTimeout(function() {
			var p = m.css("position") === "fixed";
			g.style(n, {
				top: p ? 0 : h.scrollTop(),
				left: p ? 0 : h.scrollLeft(),
				width: h.width(),
				height: h.height()
			}, true);
			m.find(".layui-layer-min").hide()
		}, 100)
	};
	g.title = function(n, m) {
		var o = f("#" + c[0] + (m || g.index)).find(c[1]);
		o.html(n)
	};
	g.close = function(o) {
		var n = f("#" + c[0] + o),
			p = n.attr("type"),
			q = "layer-anim-close";
		if (!n[0]) {
			return
		}
		var r = "layui-layer-wrap",
			m = function() {
				if (p === j.type[1] && n.attr("conType") === "object") {
					n.children(":not(." + c[5] + ")").remove();
					var u = n.find("." + r);
					for (var s = 0; s < 2; s++) {
						u.unwrap()
					}
					u.css("display", u.data("display")).removeClass(r)
				} else {
					if (p === j.type[2]) {
						try {
							var t = f("#" + c[4] + o)[0];
							t.contentWindow.document.write("");
							t.contentWindow.close();
							n.find("." + c[5])[0].removeChild(t)
						} catch (v) {}
					}
					n[0].innerHTML = "";
					n.remove()
				}
				typeof j.end[o] === "function" && j.end[o]();
				delete j.end[o]
			};
		if (n.data("isOutAnim")) {
			n.addClass(q)
		}
		f("#layui-layer-moves, #layui-layer-shade" + o).remove();
		g.ie == 6 && j.reselect();
		j.rescollbar(o);
		if (n.attr("minLeft")) {
			j.minIndex--;
			j.minLeft.push(n.attr("minLeft"))
		}
		if ((g.ie && g.ie < 10) || !n.data("isOutAnim")) {
			m()
		} else {
			setTimeout(function() {
				m()
			}, 200)
		}
	};
	g.closeAll = function(m) {
		f.each(f("." + c[0]), function() {
			var o = f(this);
			var n = m ? (o.attr("type") === m) : 1;
			n && g.close(o.attr("times"));
			n = null
		})
	};
	var a = g.cache || {},
		k = function(m) {
			return (a.skin ? (" " + a.skin + " " + a.skin + "-" + m) : "")
		};
	g.prompt = function(n, s) {
		var o = "";
		n = n || {};
		if (typeof n === "function") {
			s = n
		}
		if (n.area) {
			var q = n.area;
			o = 'style="width: ' + q[0] + "; height: " + q[1] + ';"';
			delete n.area
		}
		var m, p = n.formType == 2 ? '<textarea class="layui-layer-input"' + o + ">" + (n.value || "") + "</textarea>" : function() {
			return '<input type="' + (n.formType == 1 ? "password" : "text") + '" class="layui-layer-input" value="' + (n.value || "") + '">'
		}();
		var r = n.success;
		delete n.success;
		return g.open(f.extend({
			type: 1,
			btn: [l.btnOk, l.btnCancel],
			content: p,
			skin: "layui-layer-prompt" + k("prompt"),
			maxWidth: h.width(),
			success: function(t) {
				m = t.find(".layui-layer-input");
				m.focus();
				typeof r === "function" && r(t)
			},
			resize: false,
			yes: function(t) {
				var u = m.val();
				if (u === "") {
					m.focus()
				} else {
					if (u.length > (n.maxlength || 500)) {
						g.tips(l.promptTipA + (n.maxlength || 500) + l.promptTipB, m, {
							tips: 1
						})
					} else {
						s && s(u, t, m)
					}
				}
			}
		}, n))
	};
	g.tab = function(m) {
		m = m || {};
		var n = m.tab || {},
			o = m.success;
		delete m.success;
		return g.open(f.extend({
			type: 1,
			skin: "layui-layer-tab" + k("tab"),
			resize: false,
			title: function() {
				var p = n.length,
					q = 1,
					r = "";
				if (p > 0) {
					r = '<span class="layui-layer-tabnow">' + n[0].title + "</span>";
					for (; q < p; q++) {
						r += "<span>" + n[q].title + "</span>"
					}
				}
				return r
			}(),
			content: '<ul class="layui-layer-tabmain">' + function() {
				var p = n.length,
					q = 1,
					r = "";
				if (p > 0) {
					r = '<li class="layui-layer-tabli xubox_tab_layer">' + (n[0].content || "no content") + "</li>";
					for (; q < p; q++) {
						r += '<li class="layui-layer-tabli">' + (n[q].content || "no  content") + "</li>"
					}
				}
				return r
			}() + "</ul>",
			success: function(q) {
				var r = q.find(".layui-layer-title").children();
				var p = q.find(".layui-layer-tabmain").children();
				r.on("mousedown", function(u) {
					u.stopPropagation ? u.stopPropagation() : u.cancelBubble = true;
					var t = f(this),
						s = t.index();
					t.addClass("layui-layer-tabnow").siblings().removeClass("layui-layer-tabnow");
					p.eq(s).show().siblings().hide();
					typeof m.change === "function" && m.change(s)
				});
				typeof o === "function" && o(q)
			}
		}, m))
	};
	g.photos = function(x, r, u) {
		var o = {};
		x = x || {};
		if (!x.photos) {
			return
		}
		var s = x.photos.constructor === Object;
		var w = s ? x.photos : {},
			q = w.data || [];
		var n = w.start || 0;
		o.imgIndex = (n | 0) + 1;
		x.img = x.img || "img";
		var v = x.success;
		delete x.success;
		if (!s) {
			var t = f(x.photos),
				p = function() {
					q = [];
					t.find(x.img).each(function(y) {
						var z = f(this);
						z.attr("layer-index", y);
						q.push({
							alt: z.attr("alt"),
							pid: z.attr("layer-pid"),
							src: z.attr("layer-src") || z.attr("src"),
							thumb: z.attr("src")
						})
					})
				};
			p();
			if (q.length === 0) {
				return
			}
			r || t.on("click", x.img, function() {
				var z = f(this),
					y = z.attr("layer-index");
				g.photos(f.extend(x, {
					photos: {
						start: y,
						data: q,
						tab: x.tab
					},
					full: x.full
				}), true);
				p()
			});
			if (!r) {
				return
			}
		} else {
			if (q.length === 0) {
				return g.msg(message.noPicture)
			}
		}
		o.imgprev = function(y) {
			o.imgIndex--;
			if (o.imgIndex < 1) {
				o.imgIndex = q.length
			}
			o.tabimg(y)
		};
		o.imgnext = function(y, z) {
			o.imgIndex++;
			if (o.imgIndex > q.length) {
				o.imgIndex = 1;
				if (z) {
					return
				}
			}
			o.tabimg(y)
		};
		o.keyup = function(z) {
			if (!o.end) {
				var y = z.keyCode;
				z.preventDefault();
				if (y === 37) {
					o.imgprev(true)
				} else {
					if (y === 39) {
						o.imgnext(true)
					} else {
						if (y === 27) {
							g.close(o.index)
						}
					}
				}
			}
		};
		o.tabimg = function(y) {
			if (q.length <= 1) {
				return
			}
			w.start = o.imgIndex - 1;
			g.close(o.index);
			return g.photos(x, true, y);
			setTimeout(function() {
				g.photos(x, true, y)
			}, 200)
		};
		o.event = function() {
			o.bigimg.hover(function() {
				o.imgsee.show()
			}, function() {
				o.imgsee.hide()
			});
			o.bigimg.find(".layui-layer-imgprev").on("click", function(y) {
				y.preventDefault();
				o.imgprev()
			});
			o.bigimg.find(".layui-layer-imgnext").on("click", function(y) {
				y.preventDefault();
				o.imgnext()
			});
			f(document).on("keyup", o.keyup)
		};

		function m(A, B, z) {
			var y = new Image();
			y.src = A;
			if (y.complete) {
				return B(y)
			}
			y.onload = function() {
				y.onload = null;
				B(y)
			};
			y.onerror = function(C) {
				y.onerror = null;
				z(C)
			}
		}
		o.loadi = g.load(1, {
			shade: "shade" in x ? false : 0.9,
			scrollbar: false
		});
		m(q[n].src, function(y) {
			g.close(o.loadi);
			o.index = g.open(f.extend({
				type: 1,
				id: "layui-layer-photos",
				area: function() {
					var B = [y.width, y.height];
					var A = [f(i).width() - 100, f(i).height() - 100];
					if (!x.full && (B[0] > A[0] || B[1] > A[1])) {
						var z = [B[0] / A[0], B[1] / A[1]];
						if (z[0] > z[1]) {
							B[0] = B[0] / z[0];
							B[1] = B[1] / z[0]
						} else {
							if (z[0] < z[1]) {
								B[0] = B[0] / z[1];
								B[1] = B[1] / z[1]
							}
						}
					}
					return [B[0] + "px", B[1] + "px"]
				}(),
				title: false,
				shade: 0.9,
				shadeClose: true,
				closeBtn: false,
				move: ".layui-layer-phimg img",
				moveType: 1,
				scrollbar: false,
				moveOut: true,
				isOutAnim: false,
				skin: "layui-layer-photos" + k("photos"),
				content: '<div class="layui-layer-phimg"><img src="' + q[n].src + '" alt="' + (q[n].alt || "") + '" layer-pid="' + q[n].pid + '"><div class="layui-layer-imgsee">' + (q.length > 1 ? '<span class="layui-layer-imguide"><a href="javascript:;" class="layui-layer-iconext layui-layer-imgprev"></a><a href="javascript:;" class="layui-layer-iconext layui-layer-imgnext"></a></span>' : "") + '<div class="layui-layer-imgbar" style="display:' + (u ? "block" : "") + '"><span class="layui-layer-imgtit"><a href="javascript:;">' + (q[n].alt || "") + "</a><em>" + o.imgIndex + "/" + q.length + "</em></span></div></div></div>",
				success: function(z, A) {
					o.bigimg = z.find(".layui-layer-phimg");
					o.imgsee = z.find(".layui-layer-imguide,.layui-layer-imgbar");
					o.event(z);
					x.tab && x.tab(q[n], z);
					typeof v === "function" && v(z)
				},
				end: function() {
					o.end = true;
					f(document).off("keyup", o.keyup)
				}
			}, x))
		}, function() {
			g.close(o.loadi);
			g.msg(l.photoError, {
				time: 30000,
				btn: [l.photoNextPage, l.photoClose],
				yes: function() {
					q.length > 1 && o.imgnext(true, true)
				}
			})
		})
	};
	j.run = function(m) {
		f = m;
		h = f(i);
		c.html = f("html");
		g.open = function(p) {
			var n = new b(p);
			return n.index
		}
	};
	i.layui && layui.define ? (g.ready(), layui.define("jquery", function(m) {
		g.path = layui.cache.dir;
		j.run(layui.jquery);
		i.layer = g;
		m("layer", g)
	})) : ((typeof define === "function" && define.amd) ? define(["jquery"], function() {
		j.run(i.jQuery);
		return g
	}) : function() {
		j.run(i.jQuery);
		g.ready()
	}())
}(window);