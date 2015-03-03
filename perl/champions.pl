#!/usr/bin/perl -w

use LWP::Simple;
use WWW::Mechanize;


@aChampionsList = ();

&main();

sub main(){
	&getChampionList();
	&getChampionsData();
}

sub getChampionList(){
	my $sFileName = "champions.list";
	open(my $hFile, '<', $sFileName);

	while(my $sLine = <$hFile>){
		$sLine =~ s/\n//; # Remove new lines
		push(@aChampionsList, $sLine);
	}
	return;
}

sub getChampionsData(){
	foreach my $sChampion (@aChampionsList){
		$sChampion = lc($sChampion);
		$sChampion =~ tr/'. //d;
		print("Hero: ".$sChampion."\n");
		print("Description: \n".getChampionData($sChampion)."\n\n");
		sleep(1);
	}
	return;
}

sub getChampionData(){
	my ($sChampion) = @_;

	my $hMechanize = WWW::Mechanize->new();
	$hMechanize->get("http://gameinfo.na.leagueoflegends.com/en/game-info/champions/$sChampion/");

	return(parseData($hMechanize->content()));
}

sub parseData(){
	my ($sData) = @_;
	$sData =~ s/<br>//g;
	$sData =~ /<div class="default-1-2">\s+(.+)\s+<\/div>/;
	return($1);
}