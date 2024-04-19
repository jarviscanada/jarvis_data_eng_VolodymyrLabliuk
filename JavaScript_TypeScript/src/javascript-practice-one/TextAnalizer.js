
class Analyzer {
  constructor(content) {
      this.content = content;
      this.result = {
          contentType: "TEXT"
      };
  }

  detectHTML() {
      return /<[^! ][^>]*>/g.test(this.content);
  }

  detectCSS() {
    return /{[^}]*}/g.test(this.content);
  }

  countOccurrences(regex) {
      const matches = this.content.match(regex);
      const counts = {};
      if (matches) {
          matches.forEach(match => {
              const key = match.trim().split(/\s+/)[0];
              counts[key] = counts[key] ? counts[key] + 1 : 1;
          });
      }
      return counts;
  }


  analyze() {
      if (this.detectHTML()) {
        const tagsRegex = /(?<=<)(\w+)(?=[^/]*>)/g;
          const tagsCounts = this.countOccurrences(tagsRegex);
          this.result.contentType = "HTML";
          this.result.tags = tagsCounts;
      } else if (this.detectCSS()) {
         const cssRegex = /([^{}]+)(?=\s*{)/g;
          const cssCounts = this.countOccurrences(cssRegex);
          this.result.contentType = "CSS";
          this.result.cssTargets = cssCounts;
      } else {
          const lineCount = this.content.split('\n').filter(line => line.trim() !== '').length;
          this.result.lineNumber = lineCount;
      }
      return this.result;
  }
}

function analyzeContent(content) {
  const analyzer = new Analyzer(content);
  return analyzer.analyze();
}

console.log(analyzeContent("this is a test\nSeems to work"));
console.log(analyzeContent("body{blabla} a{color:#fff} a{ padding:0} a{ gap:0} body{} div"));
console.log(analyzeContent("<html><div></div><div></div></html>"));
