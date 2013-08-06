$(function() {
  var width = 950,
      height = 500,
      color = d3.scale.category20();

  var forceGraph = d3.layout.force()
      .charge(-300)
      .linkDistance(200)
      .size([width, height]);

  var svg = d3.select("#container").append("svg")
      .attr("width", width)
      .attr("height", height);

  var nodes = [
    {
      name: "Matt Gauger",
      favicon: "http://blog.mattgauger.com/favicon.ico",
      group: 0
    },
    {
      name: "@mathiasx",
      href: "https://twitter.com/mathiasx",
      favicon: "https://twitter.com/favicon.ico",
      group: 0
    },
    {
      name: "mathias",
      href: "https://github.com/mathias",
      favicon: "https://github.com/favicon.ico",
      group: 0
    },
    {
      name: "blog.mattgauger.com",
      href: "http://blog.mattgauger.com",
      favicon: "http://blog.mattgauger.com/favicon.ico",
      group: 0
    },
    {
      name: "goodreads",
      href: "http://www.goodreads.com/user/show/2450080-mathiasx",
      favicon: "https://goodreads.com/favicon.ico",
      group: 0
    },
    {
      name: "hackz0r links tumblog",
      href: "http://hackz0r.tumblr.com/",
      favicon: "https://tumblr.com/favicon.ico"
    },
    {
      name: "coderwall",
      href: "https://coderwall.com/mathias",
      favicon: "https://coderwall.com/favicon.ico"
    }
  ];

  var links = _.map(nodes, function(node, index) {
    return {
      source: index,
      target: 0,
      value: 1
    };
  });


  var labelAnchors = _.map(nodes, function(n) { 
    return { node: n }
  });

  forceGraph.nodes(nodes)
    .links(links)
    .start();

  var link = svg.selectAll(".link")
      .data(links)
    .enter().append("line")
      .attr("class", "link");

  var node = svg.selectAll(".node")
      .data(nodes)
      .enter().append("g")
        .attr("class", "node")
        .attr("r", 5)
        .style("fill", function(d) { return color(d.group); })
      .call(forceGraph.drag);

  node.append("rect")
    .attr("x", 0)
    .attr("y", -10)
    .attr("dx", 12)
    .attr("dy", ".35em")
    .attr("height", 22)
    .style('fill', 'white')

  node.append("a")
    .attr("xlink:href", function(d) { return d.href; })
    .append("text")
      .attr("dx", 12)
      .attr("dy", ".35em")
      .attr("style", 'font: 16px "Roboto", "Helvetica", Arial, Helvetica, sans-serif;')
      .text(function(d) { return d.name });

  node.append("image")
      .attr("xlink:href", function(d) { return d.favicon; })
      .attr("x", -8)
      .attr("y", -8)
      .attr("width", 16)
      .attr("height", 16);

  svg.selectAll("rect")
    .attr("width", function(d) {
      var index = d.index;
      width = node[0][index].childNodes[1].getBBox().width + 8;
      console.log(width);
      return width;
    });

  forceGraph.on("tick", function() {
    link.attr("x1", function(d) { return d.source.x; })
        .attr("y1", function(d) { return d.source.y; })
        .attr("x2", function(d) { return d.target.x; })
        .attr("y2", function(d) { return d.target.y; });

    node.attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; });
  });
});
