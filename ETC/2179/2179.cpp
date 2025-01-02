#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
using namespace std;

int N;
vector<pair<string,int>> v;
string cmp="";

bool compare(pair<string, int> &p1, pair<string,int> &p2){
    return p1.second < p2.second;
}

int main(){
    cin >> N;

    for(int i=0;i<N;i++){
        string s;
        cin >> s;
        v.push_back({s,i});
    }

    sort(v.begin(),v.end());

    int value=0;
    string a1="";
    string a2="";
    int start_index=0;
    int end_index=0;

    for(int i=0;i<N-1;i++){
        string s = v[i].first;
        string s2 = v[i+1].first;
        
        if(s==s2){
            continue;
        }

        int si=min(v[i].second, v[i+1].second);
        int ei=max(v[i].second, v[i+1].second);

        int cnt=0;
        string tmp="";

        for(int j=0;j<s.size();j++){
            if(s[j] != s2[j]){
                break;
            }
            cnt++;
            tmp+=s[j];
        }
        
        if(cnt > value){
            value = cnt;
            cmp=tmp;
            start_index=si;
        }
        else if(cnt == value){
            if(si < start_index){
                start_index=si;
                cmp=tmp;
                value=cnt;
            }
        }
    }

    sort(v.begin(),v.end(),compare);
    int cnt=0;

    for(int i=0;i<N;i++){
        string s=v[i].first;
        int idx=v[i].second;

        if(s.substr(0,cmp.size()) == cmp){
            cout << s << "\n";
            cnt++;
        }

        if(cnt==2){
            break;
        }
    }
}