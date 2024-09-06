#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> v;

int main(int argc, char **argv)
{
    int n, q;
    cin >> n >> q;

    for (int i = 0; i < n; i++)
    {
        int a;
        cin >> a;
        v.push_back(a);
    }

    sort(v.begin(), v.end());

    for (int i = 0; i < q; i++)
    {
        int num;
        cin >> num;

        int lower = lower_bound(v.begin(), v.end(), num) - v.begin();
        int upper = v.size() - lower - 1;

        if (v[lower] != num)
        {
            cout << "0\n";
        }
        else
        {
            cout << lower * upper << "\n";
        }
    }
    return 0;
}