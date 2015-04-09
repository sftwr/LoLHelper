#!/usr/bin/perl -w

use LWP::Simple;
use WWW::Mechanize;
use DBI;

@aChampionsList = ();

&main();

sub main(){
	unlink("champions.db");
	my $hDatabase = &connectToDatabase();
	
	&createTables($hDatabase);
	&getChampionList();
	&getChampionsData($hDatabase);
	&disconnectFromDatabase();
}

sub connectToDatabase(){
	$hDatabase = DBI->connect("dbi:SQLite:dbname=champions.db","","",{ RaiseError => 1 },);
	return $hDatabase;
}

sub disconnectFromDatabase(){
	my ($hDatbase) = @_;
	$hDatabase->disconnect();
}

sub createTables(){
	my ($hDatbase) = @_;
	my $hStatement = $hDatbase->prepare("CREATE TABLE Champions (Id int, ChampionName varchar(250), ChampionDescription varchar(2000));");
	$hStatement->execute();

	$hStatement = $hDatbase->prepare("CREATE TABLE Items (Id int, ItemName varchar(250), ItemDescription varchar(2000));");
	$hStatement->execute();

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
	my ($hDatbase) = @_;
	my $iCounter = 1;

	foreach my $sChampion (@aChampionsList){
		my $hStatement = $hDatabase->prepare(
			qq(INSERT INTO Champions (
				Id,
				ChampionName,
				ChampionDescription
				) VALUES (
				?,
				?,
				?)));

		$hStatement->execute($iCounter, $sChampion, getChampionData($sChampion));
		$iCounter++;
	}
	return;
}

sub getChampionData(){
	my ($sChampion) = @_;

	my $hMechanize = WWW::Mechanize->new();
	$sChampion = lc($sChampion);
	$hMechanize->get("http://gameinfo.na.leagueoflegends.com/en/game-info/champions/$sChampion/");

	return(parseData($hMechanize->content()));
}

sub parseData(){
	my ($sData) = @_;
	$sData =~ s/<br>//g;
	$sData =~ tr/'//d;
	$sData =~ /<div class="default-1-2">\s+(.+)\s+<\/div>/;
	return($1);
}