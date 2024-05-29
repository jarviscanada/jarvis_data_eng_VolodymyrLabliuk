var Analyzer = /** @class */ (function () {
    function Analyzer(content) {
        this.content = content;
        this.result = {
            contentType: "TEXT"
        };
    }
    Analyzer.prototype.detectHTML = function () {
        return /<[^! ][^>]*>/g.test(this.content);
    };
    Analyzer.prototype.detectCSS = function () {
        return /{[^}]*}/g.test(this.content);
    };
    Analyzer.prototype.countOccurrences = function (regex) {
        var matches = this.content.match(regex);
        var counts = {};
        if (matches) {
            matches.forEach(function (match) {
                var key = match.trim().split(/\s+/)[0];
                counts[key] = counts[key] ? counts[key] + 1 : 1;
            });
        }
        return counts;
    };
    Analyzer.prototype.analyze = function () {
        if (this.detectHTML()) {
            var tagsRegex = /(?<=<)(\w+)(?=[^/]*>)/g;
            var tagsCounts = this.countOccurrences(tagsRegex);
            this.result.contentType = "HTML";
            this.result.tags = tagsCounts;
        }
        else if (this.detectCSS()) {
            var cssRegex = /([^{}]+)(?=\s*{)/g;
            var cssCounts = this.countOccurrences(cssRegex);
            this.result.contentType = "CSS";
            this.result.cssTargets = cssCounts;
        }
        else {
            var lineCount = this.content.split('\n').filter(function (line) { return line.trim() !== ''; }).length;
            this.result.lineNumber = lineCount;
        }
        return this.result;
    };
    return Analyzer;
}());
function analyzeContent(content) {
    var analyzer = new Analyzer(content);
    return analyzer.analyze();
}
console.log(analyzeContent("this is a test\nSeems to work"));
console.log(analyzeContent("body{blabla} a{color:#fff} a{ padding:0} a{ gap:0} body{} div"));
console.log(analyzeContent("<html><div></div><div></div></html>"));
