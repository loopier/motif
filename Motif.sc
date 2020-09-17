Motif {
	classvar <all;
	// var <>kick, <>sn, <>ch, <>oh, <>rim, <>cym, <>bell, <>cl, <>sh, <>ht, <>mt, <>lt, <>acc;
    var <>motif;

	// set motifs with arrays of hit positions [1,3] == [1, \r, 4, \r]
    *new { arg degree=[], dur=[];
		var pat = (
            degree: degree,
            dur: dur,
		);
		^super.newCopyArgs(pat);
    }

	// set motifs as arrays of 1's and \r's
    *with { arg degree=[], dur=[];
		var pat = (
            degree: degree,
            dur: dur,
		);
		^super.newCopyArgs(pat);
    }

	*at { |key|
		^all.at(key)
	}

	*newFromKey { |key|
		var motif = this.at(key).deepCopy;
		motif ?? { ("Unknown motif " ++ key.asString).warn; ^nil };
		^motif;
	}

	*doesNotUnderstand { |selector, args|
		var motif = this.newFromKey(selector, args).deepCopy;
		^motif ?? { super.doesNotUnderstand(selector, args) };
	}

	*names {
		^all.keys.asArray.sort;
	}

	*directory {
		^this.names.collect(_.postln);
	}

	// returns array of given 'size' with default
	// values set to 'defaultValue', and values at
	// 'postitions' set to 'replaceValue'
    *asArray { arg positions, size=16, zeroIndex=false, defaultValue=\r, replaceValue=1;
        var arr = Array.fill(size, defaultValue);
		// zeroIndex.debug("zero");
        positions.do{|x|
			arr.put(x.asInt - zeroIndex.not.asInteger, replaceValue);
			// x.postln;
        }
        ^arr;
    }

	print {
		this.motif.keysValuesDo{|k, v| v.debug(k)};
	}

	newFromKey { |key|
		var motif = this.motif.at(key).deepCopy;
		motif ?? { ("Unknown motif " ++ key.asString).warn; ^nil };
		^motif;
	}

	doesNotUnderstand { |selector, args|
		var motif = this.newFromKey(selector, args).deepCopy;
		^motif ?? { super.doesNotUnderstand(selector, args) };
	}

	at { arg key;
		^motif.at(key);
	}

// the motifs

	*initClass {
		all = IdentityDictionary[
            // basic
        \basic -> Motif(
            degree: [0],
            dur: [1],
        ),
        \one -> Motif(
            degree: [0,2,4],
            dur: [1,0.5],
        ),
		];

		// all = all.freezeAsParent;
	}
}